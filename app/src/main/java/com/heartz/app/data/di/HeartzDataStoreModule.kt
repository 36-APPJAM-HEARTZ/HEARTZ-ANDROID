package com.heartz.app.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.heartz.app.data.datasource.local.UserLocalDataSource
import com.heartz.app.data.datasourceimpl.local.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val HEARTZ_DATASTORE = "heartz_datastore"
private val Context.heartzDataStore: DataStore<Preferences> by preferencesDataStore(
    name = HEARTZ_DATASTORE
)

@Module
@InstallIn(SingletonComponent::class)
object HeartzDataStoreModule {
    @Provides
    @Singleton
    fun provideHeartzDataStore(
        @ApplicationContext context: Context
    ): UserLocalDataSource = UserLocalDataSourceImpl(context.heartzDataStore)
}
