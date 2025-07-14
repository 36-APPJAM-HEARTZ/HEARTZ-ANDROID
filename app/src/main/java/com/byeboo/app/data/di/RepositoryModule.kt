package com.byeboo.app.data.di

import com.byeboo.app.data.repositoryimpl.QuestInProgressRepositoryImpl
import com.byeboo.app.data.repositoryimpl.QuestStateRepositoryImpl
import com.byeboo.app.data.repositoryimpl.auth.TokenRepositoryImpl
import com.byeboo.app.data.repositoryimpl.auth.UserRepositoryImpl
import com.byeboo.app.domain.repository.QuestInProgressRepository
import com.byeboo.app.domain.repository.QuestStateRepository
import com.byeboo.app.domain.repository.TokenRepository
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
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindsTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    @Singleton
    abstract fun bindsQuestStateRepository(questStateRepositoryImpl: QuestStateRepositoryImpl): QuestStateRepository

    @Binds
    @Singleton
    abstract fun bindsQuestInProgressRepository(questInProgressRepositoryImpl: QuestInProgressRepositoryImpl): QuestInProgressRepository
}
