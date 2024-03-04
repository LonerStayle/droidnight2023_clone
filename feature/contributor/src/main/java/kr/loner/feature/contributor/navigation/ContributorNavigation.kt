package kr.loner.feature.contributor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.loner.feature.contributor.ContributorRoute

fun NavController.navigateContributor(){
    this.navigate(ContributorRoute.route)
}

fun NavGraphBuilder.contributorNavGraph(
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
){
    composable(route  = ContributorRoute.route){
        ContributorRoute(
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object ContributorRoute{
    const val route = "contributor"
}