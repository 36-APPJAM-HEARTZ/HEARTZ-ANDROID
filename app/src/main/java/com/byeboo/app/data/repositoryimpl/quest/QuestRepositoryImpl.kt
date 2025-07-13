package com.byeboo.app.data.repositoryimpl.quest

import com.byeboo.app.data.datasource.remote.QuestRemoteDataSource
import javax.inject.Inject

class QuestRepositoryImpl @Inject constructor(
    private val questRemoteDataSource: QuestRemoteDataSource
) {

}