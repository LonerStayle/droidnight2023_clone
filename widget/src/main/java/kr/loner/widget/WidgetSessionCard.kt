package kr.loner.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextDefaults
import kotlinx.datetime.toJavaLocalDateTime
import kr.loner.core.model.Session

@Composable
internal fun WidgetSessionCard(session: Session) {
    val context = LocalContext.current
    Box(modifier = GlanceModifier.padding(bottom = 16.dp, end = 16.dp)) {
        Column(
            modifier = GlanceModifier.padding(16.dp).fillMaxWidth().cornerRadius(12.dp).background(
                GlanceTheme.colors.tertiaryContainer
            ).clickable(
                actionStartActivityWithSessionId(context, session.id)
            )
        ) {
            Text(
                session.toTimeString(),
                style = TextDefaults.defaultTextStyle.copy(
                    fontSize = 14.sp,
                    color = GlanceTheme.colors.onTertiaryContainer
                )
            )
            Spacer(modifier = GlanceModifier.width(4.dp))
            Text(
                session.speakers.first().name,
                style = TextDefaults.defaultTextStyle.copy(
                    fontSize = 14.sp,
                    color = GlanceTheme.colors.onTertiaryContainer
                )
            )
        }
    }
}

private fun Session.toTimeString(): String =
    "${startTime.toJavaLocalDateTime().toLocalTime()}" +
            " ~ " +
            "${endTime.toJavaLocalDateTime().toLocalTime()}"