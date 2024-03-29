package kr.loner.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LevelResponse {
    @SerialName("기타")
    ETC,

    @SerialName("초급")
    BASIC,

    @SerialName("중급")
    INTERMEDIATE,

    @SerialName("고급")
    ADVANCED,

}
