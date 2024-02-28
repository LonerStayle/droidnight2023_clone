package kr.loner.core.data.api.fake

import kr.loner.core.data.api.GithubApi
import kr.loner.core.data.model.ContributorResponse

internal class FakeGithubApi(private val contributors: List<ContributorResponse>) : GithubApi {
    override suspend fun getContributors(owner: String, name: String): List<ContributorResponse> {
        return contributors
    }

}