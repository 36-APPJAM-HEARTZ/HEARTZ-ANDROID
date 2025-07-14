package com.byeboo.app.data.di

import com.byeboo.app.data.datasource.remote.QuestInProgressDataSource
import com.byeboo.app.data.datasource.remote.QuestStateDataSource
import com.byeboo.app.data.datasource.remote.UserRemoteDataSource
import com.byeboo.app.data.datasourceimpl.remote.QuestInProgressDataSourceImpl
import com.byeboo.app.data.datasourceimpl.remote.QuestStateDataSourceImpl
import com.byeboo.app.data.datasourceimpl.remote.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(impl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsQuestStateDataSource(impl: QuestStateDataSourceImpl): QuestStateDataSource

    @Binds
    @Singleton
    abstract fun bindsQuestInProgressDataSource(impl: QuestInProgressDataSourceImpl): QuestInProgressDataSource
}
