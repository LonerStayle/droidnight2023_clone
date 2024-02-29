package kr.loner.core.domain.usecase

import kr.loner.core.data.repository.SponsorRepository
import kr.loner.core.model.Sponsor
import javax.inject.Inject

class GetSponsorListUseCase @Inject constructor(
    private val sponsorRepository: SponsorRepository
) {
    suspend operator fun invoke(): List<Sponsor> = sponsorRepository.getSponsorList()

}