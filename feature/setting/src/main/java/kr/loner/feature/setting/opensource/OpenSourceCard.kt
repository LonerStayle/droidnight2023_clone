package kr.loner.feature.setting.opensource

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import kr.loner.core.designsystem.component.LonerCard
import kr.loner.core.designsystem.theme.Graphite
import kr.loner.core.designsystem.theme.LonerTheme
import kr.loner.feature.setting.R

@Composable
internal fun OpenSourceCard(
    modifier: Modifier = Modifier,
    context: Context
) {
    val titleText = stringResource(id = R.string.oss_license_title)
    val arrowPainter = painterResource(id = R.drawable.icon_arrow_right_yellow01)

    LonerCard(
        color = Graphite,
        modifier = modifier,
        onClick = {
            context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        }
    ) {
        Column {
            Text(
                text = titleText,
                style = LonerTheme.typography.headlineSmallBL,
                modifier = Modifier.padding(top = 24.dp, start = 24.dp),
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(top = 39.dp))
            Image(
                painter = arrowPainter,
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 24.dp, bottom = 24.dp)

            )
        }
    }
}