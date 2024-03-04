package kr.loner.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.loner.core.designsystem.component.LonerCard
import kr.loner.core.designsystem.theme.Black
import kr.loner.core.designsystem.theme.Green03
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.core.designsystem.theme.Neon05

@Composable
internal fun ContributorCard(
    onClick:()->Unit
){
    LonerCard(
        color = Neon05,
        onClick = onClick,
        modifier = Modifier.height(164.dp)
    ) {
        Box(modifier = Modifier.padding(horizontal = 24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img_contributor_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
            Image(
                painter = painterResource(id = R.drawable.img_wink_android_arm),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(id = R.string.contributor_card_title),
                style = LonerTheme.typography.headlineSmallBL,
                color = Black,
                modifier = Modifier.padding(top = 24.dp),
            )

            Text(
                text = stringResource(id = R.string.contributor_card_description),
                style = LonerTheme.typography.titleSmallR,
                color = Green03,
                modifier = Modifier.padding(top = 6.dp),
            )
        }
    }
}

@Preview
@Composable
private fun ContributorCardPreview(){
    LonerTheme {
        ContributorCard {

        }
    }
}