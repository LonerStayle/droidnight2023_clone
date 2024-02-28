package kr.loner.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeakerResponse(
    val name: String,
    val introduction: String,
    val imageUrl: String,
)
