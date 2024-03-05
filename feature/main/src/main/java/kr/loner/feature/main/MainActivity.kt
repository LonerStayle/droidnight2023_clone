package kr.loner.feature.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue

import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.widget.LonerWidget.Companion.KEY_SESSION_ID

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val sessionIdFromWidget: MutableStateFlow<String?> = MutableStateFlow(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getStringExtra(KEY_SESSION_ID)?.let {
            sessionIdFromWidget.value = it
            intent.removeExtra(KEY_SESSION_ID)
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle(
                initialValue = false,
                lifecycleOwner = this
            )

            val navigator: MainNavigator = rememberMainNavigator()
            val sessionId = sessionIdFromWidget.collectAsStateWithLifecycle().value

            LaunchedEffect(sessionId) {
                sessionId?.let {
                    navigator.navigateSessionDetail(it)
                }
            }

            LonerTheme(darkTheme = isDarkTheme) {
                MainScreen(
                    navigator = navigator,
                    onChangeDarkTheme = { isDarkTheme ->
                        viewModel.updateIsDarkTheme(isDarkTheme)
                    })
            }
        }
    }
}