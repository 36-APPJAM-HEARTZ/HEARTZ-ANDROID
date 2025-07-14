package com.byeboo.app.presentation.quest.record

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.domain.model.QuestContentLengthValidator
import com.byeboo.app.domain.model.quest.QuestRecording
import com.byeboo.app.domain.repository.quest.QuestDetailRecordingRepository
import com.byeboo.app.domain.repository.quest.QuestRecordingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuestRecordingViewModel @Inject constructor(
    val questDetailRecordingRepository: QuestDetailRecordingRepository,
    val questRecordingRepository: QuestRecordingRepository
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

    fun setQuestId(questId: Long) {
        _state.update {
            it.copy(questId = questId)
        }
    }

    fun getQuestDetailInfo(questId: Long) {
        viewModelScope.launch {
            val result = questDetailRecordingRepository.getQuestRecordingDetail(questId)
            result.onSuccess { detail ->
                _state.update {
                    it.copy(
                        step = detail.step,
                        stepNumber = detail.stepNumber,
                        questNumber = detail.questNumber,
                        questQuestion = detail.question
                    )
                }
            }
        }
    }

    fun postQuestRecording() {
        val questId = state.value.questId
        val answer = state.value.questAnswer
        val emotion = state.value.selectedEmotion.title

        viewModelScope.launch {
            Log.d("questrecording","postQuestRecording() called with questId=$questId, answer=$answer, emotion=$emotion")

            val request = QuestRecording(
                answer = answer,
                questEmotionState = emotion
            )
            val result = questRecordingRepository.postRecording(questId, request)

            if (result.success) {
                _sideEffect.emit(QuestRecordingSideEffect.NavigateToQuestRecordingComplete(questId))
                Log.d("questrecording", "postQuestRecording() success: Navigate to complete")
            }
        }
    }

    fun updateContent(questAnswer: String) {
        val contentState = QuestContentLengthValidator.validate(questAnswer)
        _state.update {
            it.copy(
                questAnswer = questAnswer,
                contentsState = contentState
            )
        }
    }

    fun onBackClick() {
        _showQuitModal.value = true
    }

    fun onDismissModal() {
        _showQuitModal.value = false
    }

    fun onQuitClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestRecordingSideEffect.NavigateToQuest)
        }
    }

    fun onTipClick() {
        val questId = state.value.questId
        viewModelScope.launch {
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
