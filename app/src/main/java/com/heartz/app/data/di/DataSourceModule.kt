package com.heartz.app.data.di

import com.heartz.app.data.datasource.local.DummyLocalDataSource
import com.heartz.app.data.datasource.remote.DummyRemoteDataSource
import com.heartz.app.data.datasourceimpl.remote.DummyRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dateroad.data.datalocal.datasourceimpl.DummyLocalDataSourceImpl
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
}
