package com.heartz.app.data.di

import com.heartz.app.data.service.DummyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun providesDummyService(retrofit: Retrofit): DummyService = retrofit.create(
        DummyService::class.java
    )
}
