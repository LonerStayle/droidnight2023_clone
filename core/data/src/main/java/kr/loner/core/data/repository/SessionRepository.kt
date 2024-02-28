package kr.loner.core.data.repository

import kotlinx.coroutines.flow.Flow
import kr.loner.core.model.Session


interface SessionRepository {
    suspend fun getSessionList(): List<Session>
    suspend fun getSession(sessionId: String): Session
    suspend fun getBookmarkedSessionIds(): Flow<Set<String>>
    suspend fun bookmarkSession(sessionId: String, bookmark: Boolean)

}