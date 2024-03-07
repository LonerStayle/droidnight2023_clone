package kr.loner.feature.session

import kr.loner.core.model.Session

sealed interface SessionDetailUiState {
    data object Loading : SessionDetailUiState
    data class Success(val session: Session, val bookmarked: Boolean = false) : SessionDetailUiState
}