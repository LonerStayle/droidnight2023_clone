package kr.loner.core.data.api.fake

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kr.loner.core.data.api.GitRawApi
import kr.loner.core.data.model.SessionResponse
import kr.loner.core.data.model.SponsorResponse
import java.io.File

internal class FakeGithubRawApi(
    private val json:Json = Json{
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
):GitRawApi {
    private val sponsors = File("src/main/assets/sponsors_mock.json")
    private val sessions = File("src/main/assets/sessions_mock.json")
    override suspend fun getSponsors(): List<SponsorResponse> {
        return json.decodeFromStream(sponsors.inputStream())
    }
    override suspend fun getSessions(): List<SessionResponse> {
        return json.decodeFromStream(sessions.inputStream())
    }
}