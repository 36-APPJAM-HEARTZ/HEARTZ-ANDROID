package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.presentation.quest.model.Quest
import com.byeboo.app.presentation.quest.model.QuestGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class QuestViewModel @Inject constructor(
    // private val questRepository: QuestRepository
) : ViewModel() {
    private val _sideEffect = MutableSharedFlow<QuestSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    private val _questGroups = MutableStateFlow<List<QuestGroup>>(emptyList())
    val questGroups: StateFlow<List<QuestGroup>> = _questGroups.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentStepIndex = MutableStateFlow(0)
    val currentStepIndex: StateFlow<Int> = _currentStepIndex.asStateFlow()

    init {
        loadQuests()
    }

    private fun loadQuests() {
        viewModelScope.launch {
            _isLoading.value = true
            runCatching {
                getDummyQuestGroups()
            }.onSuccess { quests ->
                _questGroups.value = quests
                _currentStepIndex.value = calculateCurrentStep(quests)
            }

            _isLoading.value = false
        }
    }

    private fun calculateCurrentStep(questGroups: List<QuestGroup>): Int {
        return questGroups.indexOfFirst { group ->
            group.quests.any {
                it.state is QuestState.Available ||
                    it.state is QuestState.TimerLocked
            }
        }.coerceAtLeast(0)
    }

    fun onQuestClick(questId: Int) {
        viewModelScope.launch {
            val quest = questGroups.value
                .flatMap { it.quests }
                .find { it.questId == questId }

            when (quest?.state) {
                is QuestState.InProgress -> {
                    _sideEffect.emit(QuestSideEffect.NavigateToHome)
                }

                is QuestState.Available -> {
                    _sideEffect.emit(QuestSideEffect.NavigateToMypage)
                }

                else -> {
                    // todo : block
                }
            }
        }
    }

    private fun getDummyQuestGroups(): List<QuestGroup> {
        val currentStep = 22
        val nextAvailable: String? = null
        val isTimerLocked = nextAvailable != null
        val remainTime = "23:45"

        val stepTitles = listOf(
            "감정 쓸어내기",
            "상황 정리하기",
            "내 역할 돌아보기",
            "새로운 관점 찾기",
            "앞으로 나아가기"
        )

        return stepTitles.mapIndexed { stepIndex, stepTitle ->
            val startQuestNumber = stepIndex * 6 + 1
            val endQuestNumber = (stepIndex + 1) * 6

            QuestGroup(
                stepTitle = stepTitle,
                quests = (startQuestNumber..endQuestNumber).map { questNumber ->
                    val state = when {
                        questNumber < currentStep -> QuestState.InProgress
                        questNumber == currentStep -> {
                            if (isTimerLocked) {
                                QuestState.TimerLocked(remainTime)
                            } else {
                                QuestState.Available
                            }
                        }

                        else -> QuestState.Locked
                    }
                    Quest(
                        // 임시
                        questId = questNumber,
                        questNumber = questNumber,
                        state = state
                    )
                }
            )
        }
    }
}
