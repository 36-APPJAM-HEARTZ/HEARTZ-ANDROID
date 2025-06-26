package com.heartz.app.data.datasourceimpl.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.heartz.app.data.datasource.local.DummyLocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val FILE_NAME = "heartz_datastore"

private val Context.dataStore by preferencesDataStore(name = FILE_NAME)

class DummyLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DummyLocalDataSource {
    companion object {
        val IS_LOGIN = booleanPreferencesKey("is_login")
    }

    override val isLogin: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[IS_LOGIN] ?: false
        }

    override suspend fun setIsLogin(value: Boolean) {
        context.dataStore.edit { it[IS_LOGIN] = value }
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
