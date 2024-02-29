package kr.loner.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.loner.core.data.repository.SessionRepository
import kr.loner.core.model.Session
import javax.inject.Inject

class GetSessionListUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): List<Session> = sessionRepository.getSessionList()

}