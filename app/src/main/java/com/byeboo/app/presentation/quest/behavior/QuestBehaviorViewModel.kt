package com.byeboo.app.presentation.quest.behavior

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.domain.model.ContentLengthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class QuestBehaviorViewModel @Inject constructor(
//    val questBehaviorRepository: QuestBehaviorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuestBehaviorState())
    val uiState: StateFlow<QuestBehaviorState> = _uiState.asStateFlow()

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet.asStateFlow()

    private val _isEmotionSelected = MutableStateFlow(false)
    val isEmotionSelected: StateFlow<Boolean> = _isEmotionSelected.asStateFlow()

    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri

    fun updateSelectedImage(uri: Uri?) {
        _selectedImageUri.value = uri
        _uiState.update {
            it.copy(
                imageCount = if (uri != null) 1 else 0
            )
        }
    }

    fun updateContent(text: String) {
        val contentState = ContentLengthValidator.validate(text)
        _uiState.update {
            it.copy(
                contents = text,
                contentState = contentState
            )
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

    fun updateContents(content: String?) {
        _uiState.value = _uiState.value.copy(
            contents = content.toString(),
            isContentAvailable = !content.isNullOrBlank()

        )
    }
}
