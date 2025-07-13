package com.byeboo.app.data.di

import com.byeboo.app.data.repositoryimpl.DummyRepositoryImpl
import com.byeboo.app.data.repositoryimpl.auth.UserRepositoryImpl
import com.byeboo.app.domain.repository.DummyRepository
import com.byeboo.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository

    @Binds
    @Singleton
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindQuestDetailBehaviorRepository(questDetailBehaviorRepositoryImpl: QuestDetailBehaviorRepositoryImpl) : QuestDetailBehaviorRepository

    @Binds
    @Singleton
    abstract fun bindQuestDetailRecordingRepository(questDetailRecordingRepositoryImpl: QuestDetailRecordingRepositoryImpl): QuestDetailRecordingRepository

    @Binds
    @Singleton
    abstract fun bindsTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    @Singleton
    abstract fun bindsQuestStateRepository(questStateRepositoryImpl: QuestStateRepositoryImpl): QuestStateRepository
}
