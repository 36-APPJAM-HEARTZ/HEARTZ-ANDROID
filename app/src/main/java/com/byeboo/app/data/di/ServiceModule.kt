package com.byeboo.app.data.di

import com.byeboo.app.data.service.DummyService
import com.byeboo.app.data.service.QuestService
import com.byeboo.app.data.service.auth.UserService
import com.byeboo.app.data.service.quest.QuestDetailService
import com.kakao.sdk.user.model.User
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
}
