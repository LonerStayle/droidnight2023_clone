package kr.loner.core.data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class SessionResponse(
    val id:String,
    val title:String,
    val content:String,
    val speakers:List<SpeakerResponse>,
    val level:String,
    val tags:List<String>,
    val room:RoomResponse?,
    val startTime:LocalDateTime,
    val endTime:LocalDateTime,
)
