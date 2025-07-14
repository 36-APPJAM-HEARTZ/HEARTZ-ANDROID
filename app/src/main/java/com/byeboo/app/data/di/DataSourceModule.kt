package com.byeboo.app.data.di

import com.byeboo.app.data.datasource.local.DummyLocalDataSource
import com.byeboo.app.data.datasource.remote.DummyRemoteDataSource
import com.byeboo.app.data.datasource.remote.quest.QuestStateDataSource
import com.byeboo.app.data.datasource.remote.UserRemoteDataSource
import com.byeboo.app.data.datasource.remote.quest.QuestTipDataSource
import com.byeboo.app.data.datasourceimpl.local.DummyLocalDataSourceImpl
import com.byeboo.app.data.datasourceimpl.remote.DummyRemoteDataSourceImpl
import com.byeboo.app.data.datasourceimpl.remote.QuestStateDataSourceImpl
import com.byeboo.app.data.datasourceimpl.remote.QuestTipDataSourceImpl
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
    abstract fun bindDummyRemoteDataSource(impl: DummyRemoteDataSourceImpl): DummyRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindDummyLocalDataSource(impl: DummyLocalDataSourceImpl): DummyLocalDataSource

    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(impl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsQuestStateDataSource(impl: QuestStateDataSourceImpl): QuestStateDataSource

    @Binds
    @Singleton
    abstract fun bindsQuestTipDataSource(impl: QuestTipDataSourceImpl): QuestTipDataSource
}
