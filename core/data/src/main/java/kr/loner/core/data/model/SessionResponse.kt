package kr.loner.core.data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
internal data class SessionResponse(
    val id: String,
    val title: String,
    val content: String,
    val speakers: List<SpeakerResponse>,
    val level: LevelResponse,
    val tags: List<String>,
    val room: RoomResponse = RoomResponse.ETC,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
)
