package com.byeboo.app.data.repositoryimpl

import com.byeboo.app.data.datasource.remote.QuestBehaviorAnswerDataSource
import com.byeboo.app.data.dto.request.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.QuestSignedUrlRequestDto
import com.byeboo.app.domain.repository.QuestBehaviorAnswerRepository
import javax.inject.Inject

class QuestBehaviorAnswerRepositoryImpl @Inject constructor(
    private val questBehaviorAnswerDataSource: QuestBehaviorAnswerDataSource
) : QuestBehaviorAnswerRepository{

    override suspend fun postQuestSignedUrl(request: QuestSignedUrlRequestDto): String {
        return runCatching {
            val response = questBehaviorAnswerDataSource.postQuestSignedUrl(request)
            response.data.signedUrl
        }.getOrThrow()
    }

    override suspend fun postQuestBehaviorAnswer(
        questId: Long,
        request: QuestBehaviorAnswerRequestDto
    ) {
        TODO("Not yet implemented")
    }
}