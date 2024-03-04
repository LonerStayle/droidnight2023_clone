package kr.loner.feature.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kr.loner.core.domain.usecase.GetBookmarkedSessionIdListUseCase
import kr.loner.core.domain.usecase.GetSessionListUseCase
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getSessionListUseCase: GetSessionListUseCase,
    private val getBookmarkedSessionIdListUseCase: GetBookmarkedSessionIdListUseCase
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState = MutableStateFlow<SessionUiState>(SessionUiState.Loading)
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val sessionListFlow = flow { emit(getSessionListUseCase()) }
            val bookmarkedIdListFlow = getBookmarkedSessionIdListUseCase()

            sessionListFlow.combine(bookmarkedIdListFlow) { sessionList, bookmarkedIdList ->
                val enhancedSessions = sessionList.map { session ->
                    session.copy(isBookmarked = bookmarkedIdList.contains(session.id))
                }
                SessionUiState.SessionList(
                    sessionList = enhancedSessions.toPersistentList()
                )
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect { combinedUiState ->
                _uiState.value = combinedUiState
            }
        }
    }

}