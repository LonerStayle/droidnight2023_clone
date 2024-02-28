package kr.loner.core.data.repository.impl

import kr.loner.core.data.api.GithubRawApi
import kr.loner.core.data.mapper.toEntity
import kr.loner.core.data.model.SponsorResponse
import kr.loner.core.data.repository.SponsorRepository
import kr.loner.core.model.Sponsor
import javax.inject.Inject

internal class DefaultSponsorRepository @Inject constructor(
    private val githubRawApi: GithubRawApi
) : SponsorRepository {
    override suspend fun getSponsorList(): List<Sponsor> =
        githubRawApi.getSponsorList().map(SponsorResponse::toEntity)
}