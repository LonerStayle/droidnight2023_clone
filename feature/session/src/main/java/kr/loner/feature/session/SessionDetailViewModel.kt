package kr.loner.feature.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kr.loner.core.domain.usecase.BookmarkSessionUseCase
import kr.loner.core.domain.usecase.GetBookmarkedSessionIdListUseCase
import kr.loner.core.domain.usecase.GetSessionUseCase
import javax.inject.Inject

@HiltViewModel
class SessionDetailViewModel @Inject constructor(
    private val getSessionUseCase: GetSessionUseCase,
    private val getBookmarkedSessionIdListUseCase: GetBookmarkedSessionIdListUseCase,
    private val bookmarkSessionUseCase: BookmarkSessionUseCase
) : ViewModel() {

    private val _sessionUiState =
        MutableStateFlow<SessionDetailUiState>(SessionDetailUiState.Loading)
    val sessionUiState: StateFlow<SessionDetailUiState> = _sessionUiState

    private val _sessionUiEffect = MutableStateFlow<SessionDetailEffect>(SessionDetailEffect.Idle)
    val sessionUiEffect: StateFlow<SessionDetailEffect> = _sessionUiEffect


    init {
        viewModelScope.launch {
            combine(
                sessionUiState,
                getBookmarkedSessionIdListUseCase()
            ){ sessionUiState, bookmarkIds ->
                when(sessionUiState){
                    is SessionDetailUiState.Loading -> sessionUiState
                    is SessionDetailUiState.Success -> {
                        sessionUiState.copy(bookmarked = bookmarkIds.contains(sessionUiState.session.id))
                    }
                }
            }.collect{ _sessionUiState.value = it }
        }
    }

    fun fetchSession(sessionId: String) {
        viewModelScope.launch {
            val session = getSessionUseCase(sessionId)
            _sessionUiState.value = SessionDetailUiState.Success(session)
        }
    }

    fun toggleBookmark() {
        val uiState = sessionUiState.value
        if (uiState !is SessionDetailUiState.Success) {
            return
        }
        viewModelScope.launch {
            val bookmark = uiState.bookmarked
            bookmarkSessionUseCase(uiState.session.id, !bookmark)
            _sessionUiEffect.value = SessionDetailEffect.ShowToastForBookmarkState(!bookmark)
        }
    }

    fun hidePopup() {
        viewModelScope.launch {
            _sessionUiEffect.value = SessionDetailEffect.Idle
        }
    }
}