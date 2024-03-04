package kr.loner.feature.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kr.loner.core.domain.usecase.GetBookmarkedSessionListUseCase
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkedSessionListUseCase: GetBookmarkedSessionListUseCase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _bookmarkUiState = MutableStateFlow<BookmarkUiState>(BookmarkUiState.Loading)
    val bookmarkUiState: StateFlow<BookmarkUiState> = _bookmarkUiState

    init {

        viewModelScope.launch {
            combine(
                bookmarkUiState,
                getBookmarkedSessionListUseCase()
            ){ uiState, bookmarkSessions ->
                when(uiState){
                    is BookmarkUiState.Loading -> {
                        BookmarkUiState.Success(
                            isEditButtonSelected = false,
                            bookmarks = bookmarkSessions
                                .mapIndexed { index, session ->
                                    BookmarkItemUiState(
                                        index = index,
                                        session = session,
                                        isEditMode = false
                                    )
                                }
                        )
                    }

                    is BookmarkUiState.Success -> {
                        uiState.copy(
                            bookmarks =  bookmarkSessions.mapIndexed { index, session ->
                                BookmarkItemUiState(
                                    index = index,
                                    session = session,
                                    isEditMode = uiState.isEditButtonSelected
                                )
                            }
                        )
                    }
                }
            }.catch { cause: Throwable ->
                _errorFlow.emit(cause)
            }.collect{
                _bookmarkUiState.value = it
            }
        }
    }

    fun clickEditButton(){
        val state = _bookmarkUiState.value
        if(state !is BookmarkUiState.Success) return

        _bookmarkUiState.value = state.copy(
            isEditButtonSelected = state.isEditButtonSelected.not(),
            bookmarks = state.bookmarks.map { it.copy(isEditMode = !it.isEditMode.not()) }
        )
    }
}