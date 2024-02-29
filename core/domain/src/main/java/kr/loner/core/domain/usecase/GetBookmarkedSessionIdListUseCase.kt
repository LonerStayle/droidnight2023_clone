package kr.loner.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.loner.core.data.repository.SessionRepository
import javax.inject.Inject

class GetBookmarkedSessionIdListUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(): Flow<Set<String>> = sessionRepository.getBookmarkedSessionIds()

}