package kr.loner.feature.session.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kr.loner.core.model.Session
import kr.loner.feature.session.SessionDetailScreen
import kr.loner.feature.session.SessionScreen

fun NavController.navigateSession() {
    navigate(SessionRoute.route)
}

fun NavController.navigateSessionDetail(sessionId: String) {
    navigate(SessionRoute.detailRoute(sessionId))
}

fun NavGraphBuilder.sessionNavGraph(
    onBackClick: () -> Unit,
    onSessionClick: (Session) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable(SessionRoute.route) { SessionScreen() }

    composable(
        route = SessionRoute.detailRoute("{id}"),
        arguments = listOf(
            navArgument("id"){
                type = NavType.StringType
            }
        )
    ){ entry ->
        val sessionId = entry.arguments?.getString("id")?:""
        SessionDetailScreen()
    }
}

object SessionRoute {
    const val route = "session"
    fun detailRoute(sessionId: String): String = "$route/$sessionId"
}