package kr.loner.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultSessionPreferencesDataSource @Inject constructor(
    @Named("session") private val dataStore: DataStore<Preferences>,
) : SessionPreferencesDataSource {
    object PrefKey {
        val BOOKMARKED_SESSION = stringSetPreferencesKey("BOOKMARKED_SESSION")
    }

    override val bookmarkedSession: Flow<Set<String>> = dataStore.data.map { pref ->
        pref[PrefKey.BOOKMARKED_SESSION] ?: emptySet()
    }

    override suspend fun updateBookmarkedSession(bookmarkedSession: Set<String>) {
        dataStore.edit { pref ->
            pref[PrefKey.BOOKMARKED_SESSION] = bookmarkedSession
        }
    }
}