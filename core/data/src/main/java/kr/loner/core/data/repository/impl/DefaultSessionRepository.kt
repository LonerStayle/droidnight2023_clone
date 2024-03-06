package kr.loner.core.data.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kr.loner.core.data.api.GithubRawApi
import kr.loner.core.data.mapper.toEntity
import kr.loner.core.data.model.SessionResponse
import kr.loner.core.data.repository.SessionRepository
import kr.loner.core.datastore.datasource.SessionPreferencesDataSource
import kr.loner.core.model.Session
import javax.inject.Inject

internal class DefaultSessionRepository @Inject constructor(
    private val githubRawApi: GithubRawApi,
    private val sessionDataSource: SessionPreferencesDataSource
) : SessionRepository {
    private var cachedSessions: List<Session> = emptyList()

    private val bookmarkIds: Flow<Set<String>> = sessionDataSource.bookmarkedSession
    override suspend fun getSessionList(): List<Session> =
        githubRawApi.getSessionList()
            .map(SessionResponse::toEntity)
            .also { cachedSessions = it }

    override suspend fun getSession(sessionId: String): Session {
        return cachedSessions.find { it.id == sessionId }
            ?: (getSessionList().find { it.id == sessionId }
                ?: error("Session not found with id: $sessionId"))
    }

    override suspend fun getBookmarkedSessionIds(): Flow<Set<String>> {
        return bookmarkIds.filterNotNull()
    }

    override suspend fun bookmarkSession(sessionId: String, bookmark: Boolean) {
        val currentBookmarkedSessionIds = bookmarkIds.first()
        sessionDataSource.updateBookmarkedSession(
            if (bookmark) {
                currentBookmarkedSessionIds.plus(sessionId)
            } else {
                currentBookmarkedSessionIds.minus(sessionId)
            }
        )
    }
}