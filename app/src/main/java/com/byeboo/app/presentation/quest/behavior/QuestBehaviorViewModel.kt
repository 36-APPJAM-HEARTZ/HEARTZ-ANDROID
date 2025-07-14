package com.byeboo.app.presentation.quest.behavior

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.util.CoilUtils.result
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.domain.model.QuestContentLengthValidator
import com.byeboo.app.domain.model.QuestStyle
import com.byeboo.app.domain.repository.quest.QuestDetailBehaviorRepository
import com.byeboo.app.domain.repository.quest.QuestRecordedDetailRepository
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
    val questDetailBehaviorRepository: QuestDetailBehaviorRepository,
    val questRecordedDetailRepository: QuestRecordedDetailRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(QuestBehaviorState())
    val uiState: StateFlow<QuestBehaviorState> = _uiState.asStateFlow()

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

    fun setQuestId(questId: Long) {
        _uiState.update {
            it.copy(questId = questId)
        }
    }

    fun getQuestDetailInfo(questId: Long) {
        viewModelScope.launch {
            val result = questDetailBehaviorRepository.getQuestBehaviorDetail(questId)
            result.onSuccess { detail ->
                _uiState.update {
                    it.copy(
                        stepMissionTitle = detail.step,
                        stepNumber = detail.stepNumber,
                        questNumber = detail.questNumber,
                        question = detail.question
                    )
                }
            }
        }
    }

    fun getQuestRecordedDetail(questId: Long) {
        viewModelScope.launch {
            val result = questRecordedDetailRepository.getQuestRecordedDetail(questId)
            result.onSuccess { detail ->
                _uiState.update {
                    it.copy(
                        stepNumber = detail.stepNumber,
                        questNumber = detail.questNumber,
                        createdAt = detail.createdAt,
                        question = detail.question,
                        answer = detail.answer,
                        imageUrl = detail.imageUrl.toString(),
                        questEmotionState = detail.questEmotionState,
                        emotionDescription = detail.emotionDescription,
                    )
                }
            }
        }
    }

    fun updateSelectedImage(uri: Uri?) {
        _selectedImageUri.value = uri
        _uiState.update {
            it.copy(
                imageCount = if (uri != null) 1 else 0
            )
        }
    }

    fun updateContent(text: String) {
        val contentState = QuestContentLengthValidator.validate(text)
        _uiState.update {
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
        val questId = uiState.value.questId

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
        val questId = uiState.value.questId

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
        _uiState.value = _uiState.value.copy(selectedEmotion = emotion)
    }

    fun onCloseClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestBehaviorSideEffect.NavigateToQuest)
        }
    }
}
