package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.core.model.QuestType
import com.byeboo.app.domain.repository.QuestInProgressRepository
import com.byeboo.app.domain.repository.QuestStateRepository
import com.byeboo.app.presentation.quest.model.Quest
import com.byeboo.app.presentation.quest.model.QuestGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val questInProgressRepository: QuestInProgressRepository,
    private val questStateRepository: QuestStateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuestUiState())
    val uiState: StateFlow<QuestUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        loadQuests()
    }

    private fun loadQuests() {
        viewModelScope.launch {
            val journey = questStateRepository.getUserJourney()
            _uiState.update { it.copy(journeyTitle = journey ?: "") }

            questInProgressRepository.getInProgressQuest()
                .onSuccess { result ->
                    val currentStep = result.currentStep
                    val steps = result.steps

                    val questGroups = steps.map { step ->
                        QuestGroup(
                            questNumber = step.stepNumber,
                            stepTitle = step.stepTitle,
                            quests = step.quests.map { quest ->
                                val state = when {
                                    quest.questNumber < currentStep -> QuestState.Complete
                                    quest.questNumber == currentStep.toLong() -> QuestState.Available
                                    else -> QuestState.Locked
                                }
                                Quest(
                                    questId = quest.questId,
                                    questNumber = quest.questNumber,
                                    state = state,
                                    questQuestion = quest.question
                                )
                            }
                        )
                    }

                    val currentStepIndex = questGroups.indexOfFirst {
                        it.quests.any { it.state is QuestState.Available }
                    }.coerceAtLeast(0)

                    _uiState.update {
                        it.copy(
                            questGroups = questGroups,
                            currentStepIndex = currentStepIndex,
                            progressPeriod = result.progressPeriod
                        )
                    }
                }
        }
    }

    fun onQuestClick(questId: Long) {
        viewModelScope.launch {
            val quest = uiState.value.questGroups
                .flatMap { it.quests }
                .find { it.questId == questId }

            when (quest?.state) {
                is QuestState.Available -> {
                    _uiState.update { it.copy(selectedQuest = quest, showQuitModal = true) }
                }

                is QuestState.Complete -> {
                    _sideEffect.emit(
                        QuestSideEffect.NavigateToQuestReview(
                            questId = quest.questId,
                            type = quest.type
                        )
                    )
                }

                else -> {
                }
            }
        }
    }

    fun onDismissModal() {
        _uiState.update { it.copy(showQuitModal = false) }
    }

    fun onTipClick() {
        val quest = uiState.value.selectedQuest ?: return
        viewModelScope.launch {
            _sideEffect.emit(QuestSideEffect.NavigateToQuestTip(quest.questId))
        }
    }

    fun onQuestStart() {
        val quest = uiState.value.selectedQuest ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(showQuitModal = false) }
            when (quest.type) {
                QuestType.EMOTION_FACE -> _sideEffect.emit(
                    QuestSideEffect.NavigateToQuestRecording(
                        quest.questId
                    )
                )

                QuestType.EMOTION_ORGANIZE -> _sideEffect.emit(
                    QuestSideEffect.NavigateToQuestBehavior(
                        quest.questId
                    )
                )
            }
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestSideEffect.NavigateToHome)
        }
    }
}