package kr.loner.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.loner.core.designsystem.theme.LightGray
import kr.loner.core.designsystem.theme.LonerTheme


@Composable
fun BottomLogo(color: Color = LightGray){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BottomLogoHeight),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Loner`s Droid Knights 2023 ",
            style = LonerTheme.typography.labelMediumR,
            color = color
        )
    }
}

val BottomLogoHeight = 48.dp