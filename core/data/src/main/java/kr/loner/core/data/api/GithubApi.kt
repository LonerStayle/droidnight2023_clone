package kr.loner.core.data.api

import kr.loner.core.data.model.ContributorResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubApi {
    @GET("repos/{owner}/{name}/contributors")
    suspend fun getContributors(
        @Path("owner") owner: String,
        @Path("name") name: String,
    ): List<ContributorResponse>
}