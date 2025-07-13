package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.core.model.QuestType
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
    private val _uiState = MutableStateFlow(Quest())
    val state: StateFlow<Quest>
        get() = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    private val _questGroups = MutableStateFlow<List<QuestGroup>>(emptyList())
    val questGroups: StateFlow<List<QuestGroup>> = _questGroups.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentStepIndex = MutableStateFlow(0)
    val currentStepIndex: StateFlow<Int> = _currentStepIndex.asStateFlow()

    private val _selectedQuest = MutableStateFlow<Quest?>(null)
    val selectedQuest: StateFlow<Quest?> = _selectedQuest.asStateFlow()

    private val _showQuitModal = MutableStateFlow(false)
    val showQuitModal: StateFlow<Boolean>
        get() = _showQuitModal.asStateFlow()

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

    fun onQuestClick(questId: Long) {
        viewModelScope.launch {
            val quest = questGroups.value
                .flatMap { it.quests }
                .find { it.questId == questId }

            when (quest?.state) {
                is QuestState.Available -> {
                    _selectedQuest.value = quest
                    _showQuitModal.value = true
                }

                is QuestState.Complete -> {
                    _sideEffect.emit(
                        QuestSideEffect.NavigateToQuestReview(
                            questId = quest.questId,
                            questType = quest.type
                        )
                    )
                }

                else -> {
                }
            }
        }
    }

    private fun getDummyQuestGroups(): List<QuestGroup> {
        val currentStep = 25
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
                questNumber = 1,
                stepTitle = stepTitle,
                quests = (startQuestNumber..endQuestNumber).map { questNumber ->
                    val state = when {
                        questNumber < currentStep -> QuestState.Complete
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
                        //TODO:
                        questId = questNumber.toLong(),
                        questNumber = questNumber.toLong(),
                        state = state,
                        questQuestion = "연애에서 반복됐던 문제 패턴 3가지를 생각해보아요.",
                        type = if (questNumber % 2 == 0) QuestType.EMOTION_FACE else QuestType.EMOTION_ORGANIZE
                    )
                }
            )
        }
    }

    fun onDismissModal() {
        _showQuitModal.value = false
    }

    fun onTipClick() {
        val quest = _selectedQuest.value ?: return

        viewModelScope.launch {
            _sideEffect.emit(QuestSideEffect.NavigateToQuestTip(quest.questId))
        }
    }

    fun onQuestStart() {
        val quest = _selectedQuest.value
        if (quest == null) {
            return
        }

        viewModelScope.launch {
            _showQuitModal.value = false

            when (quest.type) {
                QuestType.EMOTION_FACE -> {
                    _sideEffect.emit(QuestSideEffect.NavigateToQuestRecording(quest.questId))
                }

                QuestType.EMOTION_ORGANIZE -> {
                    _sideEffect.emit(QuestSideEffect.NavigateToQuestBehavior(quest.questId))
                }
            }
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestSideEffect.NavigateToHome)
        }
    }
}
