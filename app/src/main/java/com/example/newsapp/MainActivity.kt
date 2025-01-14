package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newsapp.presentation.nvgraph.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt를 사용하여 의존성 주입을 가능하게 설정
class MainActivity : ComponentActivity() {

    // MainViewModel을 Hilt를 사용해 ViewModel로 주입받음
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 스플래시 화면 설정
        installSplashScreen().apply {
            // 스플래시 화면이 유지될 조건을 설정
            setKeepOnScreenCondition {
                viewModel.splashCondition // ViewModel의 splashCondition 값에 따라 스플래시 화면 표시
            }
        }

        setContent {
            NewsAppTheme {
                // Box는 ui 요소를 감싸는 컨테이너
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    // ViewModel에서 설정한 시작 화면 경로를 ㅏㄱ져옴
                    val startDestination = viewModel.startDestination
                    // NavGraph 호출. 시작 화면 경로를 전달.
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}