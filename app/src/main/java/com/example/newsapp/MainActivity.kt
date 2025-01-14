package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.newsapp.presentation.nvgraph.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt를 사용하여 의존성 주입을 가능하게 설정
class MainActivity : ComponentActivity() {

    // MainViewModel을 Hilt를 사용해 ViewModel로 주입받음
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        actionBar?.hide()

        // 스플래시 화면 설정
        installSplashScreen().apply {
            // 스플래시 화면이 유지될 조건을 설정
            setKeepOnScreenCondition {
                viewModel.splashCondition // ViewModel의 splashCondition 값에 따라 스플래시 화면 표시
            }
        }

        setContent {
            NewsAppTheme {

                // 시스템의 다크 모드 여부를 확인
                val isSystemInDarkMode = isSystemInDarkTheme()
                // 시스템 상태바 및 navigationBar를 제어하는 컨트롤러
                val systemController = rememberSystemUiController()

                // 시스템 UI의 상태바와 navigationBar 색상 설정
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode // 다크 모드 여부에 따라 아이콘 색상 변경
                    )
                }

                // Box는 ui 요소를 감싸는 컨테이너
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    // ViewModel에서 설정한 시작 화면 경로를 가져옴
                    val startDestination = viewModel.startDestination
                    // NavGraph 호출. 시작 화면 경로를 전달.
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}