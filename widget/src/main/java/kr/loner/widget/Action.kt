package kr.loner.widget

import android.content.Context
import android.content.Intent
import androidx.glance.action.Action
import androidx.glance.appwidget.action.actionStartActivity

internal fun actionStartActivityWithSessionId(context: Context, sessionId: String): Action =
    actionStartActivity(
        Intent(
            context.packageManager.getLaunchIntentForPackage(
                context.packageName
            )
        ).putExtra(LonerWidget.KEY_SESSION_ID, sessionId)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )

internal fun actionLaunchIntentForPackage(context: Context): Action = actionStartActivity(
    Intent(context.packageManager.getLaunchIntentForPackage(context.packageName))
)