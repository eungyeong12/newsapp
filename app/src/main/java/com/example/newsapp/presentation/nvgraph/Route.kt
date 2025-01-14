package com.example.newsapp.presentation.nvgraph

// 앱 내에서 화면 간의 이동 경로를 정의하기 위한 sealed class
// sealed class를 사용하여 특정 화면 경로만 허용하도록 제한
sealed class Route(
    val route: String // 각 화면의 경로를 나타내는 문자열
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "searchScreen")
    object BookmarkScreen : Route(route = "bookmarkScreen")
    object DetailsScreen : Route(route = "detailsScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object NewsNavigation : Route(route = "newsNavigation")
    object NewsNavigatorScreen : Route(route = "newsNavigator")
}