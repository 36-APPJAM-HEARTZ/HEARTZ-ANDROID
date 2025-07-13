package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.presentation.quest.model.Quest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class QuestTipViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(QuestTipState())
    val state: StateFlow<QuestTipState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestTipSideEffect>()
    val sideEffect: SharedFlow<QuestTipSideEffect> = _sideEffect

    fun onCloseClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestTipSideEffect.NavigateToBack)
        }
    }

    fun loadQuestTip(quest: Quest) {
        viewModelScope.launch {
            _state.value = QuestTipState(
                stepNumber = 1,
                questNumber = quest.questNumber,
                question = "이 퀘스트는 어떤 내용인가요?",
                tipQuestion = persistentListOf(
                    "어떤 상황이었나요?",
                    "그 상황에서 내가 한 행동은?",
                    "그때 느꼈던 감정은?"
                ),
                tipAnswer = persistentListOf(
                    "구체적인 상황을 써보면 좋아요.",
                    "그때 내가 어떤 행동을 했는지 되돌아보세요.",
                    "감정을 있는 그대로 써보세요."
                )
            )
        }
    }
}
