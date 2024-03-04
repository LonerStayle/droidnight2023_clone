package kr.loner.feature.bookmark.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kr.loner.feature.bookmark.BookmarkRoute

fun NavController.navigateBookmark(navOptions: NavOptions) {
    navigate(BookmarkRoute.route, navOptions)
}

fun NavGraphBuilder.bookmarkNavGraph(onShowErrorSnackBar: (throwable: Throwable?) -> Unit) {
    composable(route = BookmarkRoute.route) {
        BookmarkRoute(onShowErrorSnackBar)
    }
}


object BookmarkRoute {
    const val route = "bookmark"
}