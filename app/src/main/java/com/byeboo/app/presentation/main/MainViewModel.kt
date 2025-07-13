package com.byeboo.app.presentation.main

import androidx.lifecycle.ViewModel
import com.byeboo.app.domain.repository.QuestStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val questStateRepository: QuestStateRepository
) : ViewModel() {
    suspend fun isQuestStarted(): Boolean {
        return questStateRepository.isQuestStarted()
    }
}
