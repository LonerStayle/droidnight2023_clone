package kr.loner.feature.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.core.designsystem.theme.Purple01
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
internal fun BookmarkTimelineItem(
    modifier: Modifier = Modifier,
    sequence: Int,
    time: LocalTime
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SequenceBadge(sequence = sequence)
        Spacer(modifier = Modifier.height(8.dp))
        SessionTimeBadge(time = time)
    }
}

@Composable
private fun SequenceBadge(
    modifier: Modifier = Modifier,
    sequence: Int
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(color = Purple01.copy(alpha = 0.3f), shape = CircleShape)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = sequence.toString(),
            style = LonerTheme.typography.labelLargeM,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SessionTimeBadge(
    modifier: Modifier = Modifier,
    time: LocalTime
) {
    val pattern = stringResource(id = R.string.session_time_format)
    val formatter = remember { DateTimeFormatter.ofPattern(pattern) }

    Box(
        modifier = modifier
            .background(color = Purple01, shape = RoundedCornerShape(percent = 50))
            .padding(vertical = 2.dp, horizontal = 8.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text =  formatter.format(time),
            style = LonerTheme.typography.labelSmallM,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}