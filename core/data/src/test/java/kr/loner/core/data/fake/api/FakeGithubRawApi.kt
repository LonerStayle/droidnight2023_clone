package kr.loner.core.data.fake.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kr.loner.core.data.api.GithubRawApi
import kr.loner.core.data.model.SessionResponse
import kr.loner.core.data.model.SponsorResponse
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
internal class FakeGithubRawApi(
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
) : GithubRawApi {
    private val sponsors = File("src/main/assets/sponsors_mock.json")
    private val sessions = File("src/main/assets/sessions_mock.json")
    override suspend fun getSponsorList(): List<SponsorResponse> {
        return json.decodeFromStream(sponsors.inputStream())
    }

    override suspend fun getSessionList(): List<SessionResponse> {
        return json.decodeFromStream(sessions.inputStream())
    }
}