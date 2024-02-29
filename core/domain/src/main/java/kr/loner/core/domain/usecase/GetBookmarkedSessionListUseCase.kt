package kr.loner.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kr.loner.core.model.Session
import javax.inject.Inject

class GetBookmarkedSessionListUseCase @Inject constructor(
    private val getBookmarkedSessionIdListUseCase: GetBookmarkedSessionIdListUseCase,
    private val getSessionListUseCase: GetSessionListUseCase
) {
    suspend operator fun invoke(): Flow<List<Session>> {
        return  flow {
            emit(getSessionListUseCase())
        }.combine(getBookmarkedSessionIdListUseCase()){ allSessions, bookmarkIds ->
            allSessions
                .filter { session -> bookmarkIds.contains(session.id)}
                .sortedBy { it.startTime }
        }
    }

}