package kr.loner.feature.main

import kr.loner.app2023.feature.main.R
import kr.loner.feature.bookmark.navigation.BookmarkRoute
import kr.loner.feature.home.navigation.HomeRoute
import kr.loner.feature.setting.navigation.SettingRoute

internal enum class MainTab (
    val iconResId:Int,
    internal val contentDescription:String,
    val route:String,
){
    SETTING(
        iconResId = R.drawable.ic_setting,
        contentDescription = "설정",
        route = SettingRoute.route
    ),
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = "홈",
        route = HomeRoute.route
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_bookmark,
        contentDescription = "북마크",
        route = BookmarkRoute.route
    );

    companion object{
        operator fun contains(route: String):Boolean{
            return entries.map { it.route }.contains(route)
        }

        fun find(route:String):MainTab?{
            return entries.find { it.route == route }
        }
    }
}