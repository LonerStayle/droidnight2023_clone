package kr.loner.feature.session

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.loner.core.designsystem.theme.Graphite
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.core.designsystem.theme.Purple01

@Composable
internal fun SessionDetailBookmarkStatePopup(bookmarked:Boolean){
    val messageStringRes = if(bookmarked){
        R.string.session_detail_bookmark_popup_message
    }else{
        R.string.session_detail_unbookmark_popup_message
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(4.dp),
        color= Graphite
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 15.dp),
            text = stringResource(id = messageStringRes),
            style = LonerTheme.typography.bodyMediumR,
            color = Purple01
        )
    }
}