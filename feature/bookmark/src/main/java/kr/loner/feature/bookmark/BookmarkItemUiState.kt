package kr.loner.feature.bookmark

import kotlinx.datetime.toJavaLocalDateTime
import kr.loner.core.model.Session
import java.time.LocalTime

data class BookmarkItemUiState(
    val index: Int,
    val session: Session,
    val isEditMode: Boolean
){
    val sequence:Int
        get() = index + 1

    val tagLabel:String
        get() = session.tags.joinToString { it.name }

    val speakerLabel:String
        get() = session.speakers.joinToString { it.name }

    val time:LocalTime
        get() = session.startTime.toJavaLocalDateTime().toLocalTime()
}
