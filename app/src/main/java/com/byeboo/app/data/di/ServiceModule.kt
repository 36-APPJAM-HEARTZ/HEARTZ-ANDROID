package com.byeboo.app.data.di

import com.byeboo.app.data.service.DummyService
import com.byeboo.app.data.service.QuestBehaviorService
import com.byeboo.app.data.service.quest.QuestService
import com.byeboo.app.data.service.auth.UserService
import kotlin.jvm.java
import com.byeboo.app.data.service.quest.QuestDetailService
import com.byeboo.app.data.service.quest.QuestTipService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesDummyService(retrofit: Retrofit): DummyService = retrofit.create(
        DummyService::class.java
    )

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit): UserService = retrofit.create(
        UserService::class.java
    )

    @Provides
    @Singleton
    fun providesQuestDetailService(retrofit: Retrofit): QuestDetailService = retrofit.create(
        QuestDetailService::class.java
    )


    @Provides
    @Singleton
    fun providesQuestService(retrofit: Retrofit): QuestService = retrofit.create(
        QuestService::class.java
    )

    @Provides
    @Singleton
    fun providesQuestBehaviorService(retrofit: Retrofit): QuestBehaviorService = retrofit.create(
        QuestBehaviorService::class.java
    )

    @Provides
    @Singleton
    fun provideQuestTipService(retrofit: Retrofit): QuestTipService = retrofit.create(
        QuestTipService::class.java
    )
}
