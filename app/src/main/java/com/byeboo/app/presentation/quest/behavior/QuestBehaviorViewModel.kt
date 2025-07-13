package com.byeboo.app.presentation.quest.behavior

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.domain.model.QuestContentLengthValidator
import com.byeboo.app.domain.model.QuestDetailModel
import com.byeboo.app.domain.model.QuestStyle
import com.byeboo.app.domain.repository.quest.QuestDetailBehaviorRepository
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
class QuestBehaviorViewModel @Inject constructor(
    val questDetailBehaviorRepository: QuestDetailBehaviorRepository
) : ViewModel() {
    private val _state = MutableStateFlow(QuestBehaviorState())
    val state: StateFlow<QuestBehaviorState> = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestBehaviorSideEffect>()
    val sideEffect: SharedFlow<QuestBehaviorSideEffect> = _sideEffect

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet.asStateFlow()

    private val _isEmotionSelected = MutableStateFlow(false)
    val isEmotionSelected: StateFlow<Boolean> = _isEmotionSelected.asStateFlow()

    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri

    private val _selectedQuest = MutableStateFlow<Quest?>(null)
    val selectedQuest: StateFlow<Quest?> = _selectedQuest.asStateFlow()

    private val _showQuitModal = MutableStateFlow(false)
    val showQuitModal: StateFlow<Boolean>
        get() = _showQuitModal.asStateFlow()

//    fun getQuestDetailInfo(questId: Long) {
//        viewModelScope.launch {
//
//            val result = questDetailBehaviorRepository.getQuestBehaviorDetail(questId)
//
//            if (result.isSuccess) {
//                _sideEffect.emit(QuestBehaviorSideEffect.NavigateToQuest)
//            }
//
//            val questDetail = QuestDetailModel(
//                step = _state.value.stepMissionTitle,
//                stepNumber = _state.value.stepNumber,
//                questNumber = _state.value.questNumber,
//                questStyle = QuestStyle.ACTIVE,
//                question = _state.value.questTitle
//            )
//        }
//    }



    private fun calculateStepNumber(questNumber: Int): Int {
        return ((questNumber - 1) / 6) + 1
    }

    private fun calculateStepTitle(questNumber: Int): String {
        val stepTitles = listOf(
            "감정 쓸어내기",
            "상황 정리하기",
            "내 역할 돌아보기",
            "새로운 관점 찾기",
            "앞으로 나아가기"
        )
        val stepIndex = ((questNumber - 1) / 6).coerceIn(0, stepTitles.size - 1)
        return stepTitles[stepIndex]
    }

    fun setQuestId(questId: Long) {
        _state.update {
            it.copy(questId = questId)
        }
    }

    fun updateSelectedImage(uri: Uri?) {
        _selectedImageUri.value = uri
        _state.update {
            it.copy(
                imageCount = if (uri != null) 1 else 0
            )
        }
    }

    fun updateContent(text: String) {
        val contentState = QuestContentLengthValidator.validate(text)
        _state.update {
            it.copy(
                contents = text,
                contentState = contentState
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
        val questId = state.value.questId

        viewModelScope.launch {
            _sideEffect.emit(QuestBehaviorSideEffect.NavigateToQuestBehaviorComplete(questId))
        }
    }

    fun onQuitClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestBehaviorSideEffect.NavigateToQuest)
        }
    }

    fun onTipClick() {
        val questId = state.value.questId

        viewModelScope.launch {
            _sideEffect.emit(QuestBehaviorSideEffect.NavigateToQuestTip(questId))
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

    fun onCloseClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestBehaviorSideEffect.NavigateToQuest)
        }
    }
}
