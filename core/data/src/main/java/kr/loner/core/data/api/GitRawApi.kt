package kr.loner.core.data.api

import kr.loner.core.data.model.SessionResponse
import kr.loner.core.data.model.SponsorResponse
import retrofit2.http.GET

internal interface GitRawApi {
    @GET("/LonerStayle/droidnight2023_clone/main/core/data/src/main/assets/sponsors_mock.json")
    suspend fun getSponsors(): List<SponsorResponse>

    @GET("/LonerStayle/droidnight2023_clone/main/core/data/src/main/assets/sessions_mock.json")
    suspend fun getSessions(): List<SessionResponse>
}