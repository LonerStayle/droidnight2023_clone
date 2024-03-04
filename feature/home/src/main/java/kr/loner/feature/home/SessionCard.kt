package kr.loner.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.loner.core.designsystem.component.LonerCard
import kr.loner.core.designsystem.theme.Black
import kr.loner.core.designsystem.theme.Graphite
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.core.designsystem.theme.White

@Composable
internal fun SessionCard(
    onClick: () -> Unit,
) {
    LonerCard(
        onClick = onClick,
        modifier = Modifier
            .height(164.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_card_session),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            SessionCardCaption()
            Text(
                text = stringResource(id = R.string.session_card_title),
                style = LonerTheme.typography.headlineSmallBL,
                color = Black,
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }
}

@Composable
private fun SessionCardCaption() {
    Box(
        modifier = Modifier
            .background(Graphite, RoundedCornerShape(32.dp))
            .padding(horizontal = 12.dp, vertical = 2.dp)
    ) {
        Text(
            text = stringResource(id = R.string.session_card_caption),
            style = LonerTheme.typography.labelSmallM,
            color = White,
        )
    }
}

@Preview
@Composable
private fun SessionCardPreview() {
    LonerTheme {
        SessionCard(
            onClick = { }
        )
    }
}
