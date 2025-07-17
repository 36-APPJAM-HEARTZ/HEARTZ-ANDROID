package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.domain.repository.quest.QuestRecordedDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class QuestReviewViewModel @Inject constructor(
    val questRecordedDetailRepository: QuestRecordedDetailRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(QuestReviewState())
    val uiState: StateFlow<QuestReviewState>
        get() = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestReviewSideEffect>()
    val sideEffect: SharedFlow<QuestReviewSideEffect>
        get() = _sideEffect

    fun setQuestId(questId: Long) {
        _uiState.update {
            it.copy(questId = questId)
        }
    }

    fun getQuestRecordedDetail(questId: Long) {
        viewModelScope.launch {
            val result = questRecordedDetailRepository.getQuestRecordedDetail(questId)
            result.onSuccess { detail ->
                _uiState.update {
                    val imageURL = detail.imageUrl?.takeIf { it.toString() != "null" }?.toString()

                    val newState = it.copy(
                        stepNumber = detail.stepNumber,
                        questNumber = detail.questNumber,
                        createdAt = detail.createdAt,
                        question = detail.question,
                        answer = detail.answer,
                        imageUrl = imageURL,
                        selectedEmotion = LargeTagType.fromKorean(detail.questEmotionState),
                        emotionDescription = detail.emotionDescription
                    )
                    newState
                }
            }
        }
    }
}
