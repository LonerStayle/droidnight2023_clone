package kr.loner.core.data.api.fake

import android.content.Context
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kr.loner.core.data.api.GithubRawApi
import kr.loner.core.data.model.SessionResponse
import kr.loner.core.data.model.SponsorResponse


@OptIn(ExperimentalSerializationApi::class)
internal class AssetsGithubRawApi(
    context: Context,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

):GithubRawApi {
    private val sponsors = context.assets.open("sponsors_mock.json")
    private val sessions = context.assets.open("sessions_mock.json")
    override suspend fun getSponsorList(): List<SponsorResponse> {
        return json.decodeFromStream(sponsors)
    }
    override suspend fun getSessionList(): List<SessionResponse> {
         return json.decodeFromStream(sessions)
    }
}