package kr.loner.core.data.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.loner.core.data.repository.SettingsRepository
import kr.loner.core.datastore.datasource.SettingsPreferencesDataSource
import javax.inject.Inject

class DefaultSettingsRepository @Inject constructor(
    private val preferencesDataSource: SettingsPreferencesDataSource
) : SettingsRepository {
    override fun getIsDarkTheme(): Flow<Boolean> =
        preferencesDataSource.settingData.map { it.isDarkTheme }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        preferencesDataSource.updateIsDarkTheme(isDarkTheme)
    }
}