package kr.loner.core.domain.usecase

import kr.loner.core.data.repository.ContributorRepository
import kr.loner.core.model.Contributor
import javax.inject.Inject


class GetContributorListUseCase @Inject constructor(
    private val contributorRepository: ContributorRepository
) {
    suspend operator fun invoke(): List<Contributor> =
        contributorRepository.getContributors(OWNER, NAME)

    companion object {
        private const val OWNER = "droidknights"
        private const val NAME = "DroidKnights2023_App"
    }
}