package kr.loner.feature.bookmark

sealed interface BookmarkUiState {
    data object Loading : BookmarkUiState
    data class Success(
        val isEditButtonSelected: Boolean = false,
        val bookmarks: List<BookmarkItemUiState> = emptyList()
    ):BookmarkUiState
}