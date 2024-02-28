package kr.loner.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import kr.loner.core.datastore.model.SettingData
import javax.inject.Inject
import javax.inject.Named

class SettingsPreferencesDataSource @Inject constructor(
    @Named("setting") private val dataStore: DataStore<Preferences>
) {
    object PrefKey {
        val IS_DARK_THEME = booleanPreferencesKey("IS_DARK_THEME")
    }

    val settingData = dataStore.data.map { pref ->
        SettingData(
            isDarkTheme = pref[PrefKey.IS_DARK_THEME] ?: false
        )
    }

    suspend fun updateIsDarkTheme(isDarkTheme:Boolean){
        dataStore.edit { pref ->
            pref[PrefKey.IS_DARK_THEME] = isDarkTheme
        }
    }
}