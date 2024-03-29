package kr.loner.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class RoomResponse {
    ETC,

    @SerialName("Track1")
    TRACK1,

    @SerialName("Track2")
    TRACK2,

    @SerialName("Track3")
    TRACK3
}
