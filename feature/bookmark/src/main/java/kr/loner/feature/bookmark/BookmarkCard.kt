package kr.loner.feature.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.loner.core.designsystem.theme.Green04
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.core.designsystem.theme.Purple01
import kr.loner.core.designsystem.theme.White
import kr.loner.core.model.Room
import kr.loner.core.ui.RoomText

@Composable
internal fun BookmarkCard(
    modifier: Modifier = Modifier,
    tagLabel: String,
    room: Room,
    title: String,
    speaker: String
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(8.dp))
            .padding(start = 16.dp, end = 18.dp, top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(color = Purple01, shape = CircleShape)
                    .size(12.dp)
            )
            Text(
                modifier = Modifier.weight(1f),
                text = tagLabel,
                style = LonerTheme.typography.labelSmallM,
                color = Color.DarkGray
            )
            RoomText(
                room = room,
                style = LonerTheme.typography.labelSmallM,
                color = Color.DarkGray
            )
        }

        Text(
            text = title,
            style = LonerTheme.typography.titleSmallB,
            color = Green04
        )
        Text(
            text = speaker,
            style = LonerTheme.typography.titleSmallM,
            color = Green04
        )
    }
}

@Preview
@Composable
private fun BookmarkCardPreview() {
    LonerTheme {
        Box {
            BookmarkCard(tagLabel = "머라머라", room = Room.TRACK2, title = "제목제목", speaker = "로너")
        }
    }
}