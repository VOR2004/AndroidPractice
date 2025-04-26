package ru.itis.androidpractice.data.local.preferences.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.itis.androidpractice.domain.preferences.UserPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
): UserPreferences {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
    private val IS_SIGNED_IN = booleanPreferencesKey("is_signed_in")

    override val isSignedInFlow: Flow<Boolean> = context.dataStore.data.map {
        prefs -> prefs[IS_SIGNED_IN] ?: false
    }

    override suspend fun setSignedIn(signedIn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_SIGNED_IN] = signedIn
        }
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }

}