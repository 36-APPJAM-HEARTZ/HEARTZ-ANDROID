package com.byeboo.app.presentation.quest.record

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.domain.model.QuestContentLengthValidator
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.model.Quest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestRecordingViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(QuestRecordingState())
    val state: StateFlow<QuestRecordingState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestRecordingSideEffect>()
    val sideEffect: SharedFlow<QuestRecordingSideEffect>
        get() = _sideEffect

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet.asStateFlow()

    private val _isEmotionSelected = MutableStateFlow(false)
    val isEmotionSelected: StateFlow<Boolean> = _isEmotionSelected.asStateFlow()

    private val _showQuitModal = MutableStateFlow(false)
    val showQuitModal: StateFlow<Boolean>
        get() = _showQuitModal.asStateFlow()

    private val _questId = MutableStateFlow<Int?>(null)
    val currentQuestId: Int?
        get() = _questId.value

    fun setQuestId(id: Int) {
        _questId.value = id
    }

    fun updateContent(text: String) {
        val contentState = QuestContentLengthValidator.validate(text)
        _state.update {
            it.copy(
                contents = text,
                contentsState = contentState
            )
        }
    }

    fun onBackClicked() {
        _showQuitModal.value = true
    }

    fun onDismissModal() {
        _showQuitModal.value = false
    }

    fun onCompleteClick() {
        val questId = currentQuestId ?: return
        Log.d("QuestDebug", "selectedQuest = $questId")


        viewModelScope.launch {
            _sideEffect.emit(QuestRecordingSideEffect.NavigateToQuestRecordingComplete(questId))
            Log.d("QuestDebug", "selectedQuest = $questId")

        }
    }

    fun onQuitClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestRecordingSideEffect.NavigateToQuest)
        }
    }

    fun onTipClick() {
        val questId = currentQuestId
        Log.d("QuestDebug", "selectedQuest = $questId")
        if (questId == null) {
            Log.e("QuestDebug", "selectedQuest가 null입니다. emit하지 않음.")
            return
        }

        viewModelScope.launch {
            Log.d("QuestDebug", "Emit NavigateToQuestTip(${questId})")
            _sideEffect.emit(QuestRecordingSideEffect.NavigateToQuestTip(questId))
        }
    }

    fun openBottomSheet() {
        _showBottomSheet.value = true
    }

    fun closeBottomSheet() {
        _showBottomSheet.value = false
    }

    fun isEmotionSelected(isSelected: Boolean) {
        _isEmotionSelected.value = isSelected
    }

    fun updateSelectedEmotion(emotion: LargeTagType) {
        _state.value = _state.value.copy(selectedEmotion = emotion)
    }
}