package kr.loner.core.domain.usecase

import kr.loner.core.data.repository.SessionRepository
import javax.inject.Inject

class BookmarkSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(sessionId: String, bookmark: Boolean) {
        sessionRepository.bookmarkSession(sessionId, bookmark)
    }

}