package kr.loner.feature.session

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import kotlinx.datetime.LocalDateTime
import kr.loner.core.designsystem.component.LonerCard
import kr.loner.core.designsystem.component.NetworkImage
import kr.loner.core.designsystem.component.TextChip
import kr.loner.core.designsystem.theme.DarkGray
import kr.loner.core.designsystem.theme.LightGray
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.core.model.Level
import kr.loner.core.model.Room
import kr.loner.core.model.Session
import kr.loner.core.model.Speaker
import kr.loner.core.model.Tag

@Composable
internal fun SessionCard(
    session: Session,
    modifier: Modifier = Modifier,
    onSessionClick: (Session) -> Unit = { },
) {
    if (session.content.isBlank()) {
        LonerCard(
            modifier = modifier
        ) {
            SessionCardContent(session = session)
        }
    } else {
        LonerCard(
            modifier = modifier,
            onClick = { onSessionClick(session) }
        ) {
            SessionCardContent(session = session)
        }
    }
}

@Composable
private fun SessionCardContent(
    session: Session,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        if (session.isBookmarked) {
            BookmarkImage(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 30.dp)
            )
        }
        Column(
            modifier = Modifier.padding(CardContentPadding)
        ) {
            SessionHeader(session)
            Spacer(modifier = Modifier.height(8.dp))
            SessionTitle(session.title)
            Spacer(modifier = Modifier.height(12.dp))
            SessionTrackInfo(session)
            Spacer(modifier = Modifier.height(12.dp))
            SessionSpeakers(session.speakers)
        }
    }
}

@Composable
private fun SessionHeader(
    session: Session,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryChip()
        session.tags.forEach { tag ->
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = tag.name,
                style = LonerTheme.typography.labelLargeM,
                color = DarkGray,
            )
        }
    }
}

@Composable
private fun SessionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = LonerTheme.typography.titleLargeB,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = modifier.padding(end = 50.dp)
    )
}

@Composable
private fun SessionTrackInfo(
    session: Session,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        TrackChip(room = session.room)
        Spacer(modifier = Modifier.width(8.dp))
        TimeChip(dateTime = session.startTime)
    }
}

@Composable
private fun SessionSpeakers(
    speakers: List<Speaker>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            speakers.forEach { speaker ->
                Text(
                    text = speaker.name,
                    style = LonerTheme.typography.titleLargeB,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
        Row(modifier = Modifier.align(Alignment.BottomEnd)) {
            speakers.forEach { speaker ->
                NetworkImage(
                    imageUrl = speaker.imageUrl,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    placeholder = painterResource(id = kr.loner.core.ui.R.drawable.placeholder_speaker),
                )
            }
        }
    }
}

@Composable
private fun CategoryChip() {
    TextChip(
        text = stringResource(id = R.string.session_category),
        containerColor = DarkGray,
        labelColor = LightGray,
    )
}

@Composable
private fun BookmarkImage(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_flagbookmark),
        contentDescription = null,
        modifier = modifier
            .size(
                width = 24.dp,
                height = 36.dp
            )
    )
}

private val CardContentPadding =
    PaddingValues(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 24.dp)

@Preview
@Composable
private fun SessionCardPreview() {
    val fakeSession = Session(
        id = "1",
        title = "Jetpack Compose에 있는 것, 없는 것",
        content = "",
        speakers = listOf(
            Speaker(
                name = "안성용",
                introduction = "안드로이드 개발자",
                imageUrl = "https://picsum.photos/200",
            ),
        ),
        level = Level.BASIC,
        tags = listOf(
            Tag("효율적인 코드베이스")
        ),
        startTime = LocalDateTime(2023, 9, 12, 16, 10, 0),
        endTime = LocalDateTime(2023, 9, 12, 16, 45, 0),
        room = Room.TRACK1,
        isBookmarked = false,
    )

    LonerTheme {
        SessionCard(fakeSession)
    }
}
