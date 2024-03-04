package kr.loner.feature.session

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kr.loner.core.model.Session

sealed interface SessionUiState {
    data object Loading:SessionUiState
    data class SessionList(
        val sessionList:PersistentList<Session> = persistentListOf()
    ) :SessionUiState
}