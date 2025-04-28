package ru.itis.androidpractice.data.local.preferences.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.itis.androidpractice.data.local.preferences.UserPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferences {

    private val IS_SIGNED_IN = booleanPreferencesKey("is_signed_in")

    override val isSignedInFlow: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[IS_SIGNED_IN] ?: false
    }

    override suspend fun setSignedIn(signedIn: Boolean) {
        dataStore.edit { prefs ->
            prefs[IS_SIGNED_IN] = signedIn
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}