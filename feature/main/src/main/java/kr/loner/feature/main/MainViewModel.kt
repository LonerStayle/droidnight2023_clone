package kr.loner.feature.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.loner.core.data.repository.SettingsRepository
import kr.loner.widget.sendWidgetUpdateCommand
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application:Application,
    private val settingsRepository: SettingsRepository
):ViewModel() {
    val isDarkTheme = settingsRepository.getIsDarkTheme()

    init {
        sendWidgetUpdateCommand(application)
    }

    fun updateIsDarkTheme(isDarkTheme:Boolean) = viewModelScope.launch {
        settingsRepository.updateIsDarkTheme(isDarkTheme)
    }
}