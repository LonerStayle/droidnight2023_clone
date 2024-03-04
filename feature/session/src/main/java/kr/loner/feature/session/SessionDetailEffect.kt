package kr.loner.feature.session

sealed interface SessionDetailEffect {
    data object Idle : SessionDetailEffect
    data class ShowToastForBookmarkState(val bookmarked: Boolean) : SessionDetailEffect
}