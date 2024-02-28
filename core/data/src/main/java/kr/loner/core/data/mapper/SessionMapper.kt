package kr.loner.core.data.mapper

import kr.loner.core.data.model.LevelResponse
import kr.loner.core.data.model.RoomResponse
import kr.loner.core.data.model.SessionResponse
import kr.loner.core.data.model.SpeakerResponse
import kr.loner.core.model.Level
import kr.loner.core.model.Room
import kr.loner.core.model.Session
import kr.loner.core.model.Speaker
import kr.loner.core.model.Tag

internal fun SessionResponse.toEntity() = Session(
    id = this.id,
    title = this.title,
    content = this.content,
    speakers = this.speakers.map(SpeakerResponse::toEntity),
    level = this.level.toEntity(),
    tags = this.tags.map {Tag(it)},
    room = this.room.toEntity(),
    startTime = this.startTime,
    endTime = this.endTime,
    isBookmarked = false
)

internal fun SpeakerResponse.toEntity() = Speaker(name, introduction, imageUrl)

internal fun LevelResponse.toEntity():Level = when (this) {
    LevelResponse.ETC -> Level.ETC
    LevelResponse.BASIC -> Level.BASIC
    LevelResponse.INTERMEDIATE -> Level.INTERMEDIATE
    LevelResponse.ADVANCED -> Level.ADVANCED
}


internal fun RoomResponse.toEntity():Room = when (this) {
    RoomResponse.ETC -> Room.ETC
    RoomResponse.TRACK1 -> Room.TRACK1
    RoomResponse.TRACK2 -> Room.TRACK2
    RoomResponse.TRACK3 -> Room.TRACK3
}
