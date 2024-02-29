package kr.loner.core.domain.usecase

import kr.loner.core.data.repository.SessionRepository
import kr.loner.core.model.Session
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(sessionId: String): Session =
        sessionRepository.getSession(sessionId)

}