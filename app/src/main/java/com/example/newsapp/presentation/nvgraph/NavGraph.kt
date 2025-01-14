package com.example.newsapp.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.onboarding.OnBoardingScreen
import com.example.newsapp.presentation.onboarding.OnBoardingViewModel

// 앱의 화면 이동 경로를 설정하고, 각 화면에서 필요한 구성 요소를 주입
@Composable
fun NavGraph(
    startDestination: String // 네비게이션의 시작 화면 경로
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        // 앱 시작 시 네비게이션 루트 정의
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route // OnBoarding 화면을 시작점으로 설정
        ) {
            composable(
                route = Route.OnBoardingScreen.route // OnBoarding 화면의 경로
            ) {
                // Hilt을 사용하여 OnBoardingViewModel을 주입
                val viewModel: OnBoardingViewModel = hiltViewModel()

                // OnBoardingScreen 호출 및 이벤트 처리 전달
                OnBoardingScreen(
                    event = {
                        viewModel::onEvent // ViewModel의 onEvent 함수로 이벤트 전달.
                    }
                )
            }
        }

        // 뉴스 네비게이션 루트 정의
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route // 뉴스 네비게이터 화면을 시작점으로 설정
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                Text(text = "News Navigator Screen")
            }
        }
    }
}