package kr.loner.core.data.repository.impl

import kr.loner.core.data.api.GithubApi
import kr.loner.core.data.mapper.toEntity
import kr.loner.core.data.model.ContributorResponse
import kr.loner.core.data.repository.ContributorRepository
import kr.loner.core.model.Contributor
import javax.inject.Inject

internal class DefaultContributorRepository @Inject constructor(
    private val githubApi: GithubApi
) : ContributorRepository {
    override suspend fun getContributors(owner: String, name: String): List<Contributor> {
        return githubApi.getContributors(owner, name).map(ContributorResponse::toEntity)
    }
}