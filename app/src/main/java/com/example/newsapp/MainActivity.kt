package com.example.newsapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.presentation.onboarding.OnBoardingScreen
import com.example.newsapp.presentation.onboarding.OnBoardingViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt를 사용하여 의존성 주입을 가능하게 설정
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen() // 앱 시작 시 스플래시 화면 표시

        setContent {
            NewsAppTheme {
                // Box는 ui 요소를 감싸는 컨테이너
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    // Hilt을 사용하여 OnBoardingViewModel을 주입
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    // OnBoardingScreen을 호출하며 이벤트 처리 콜백 전달
                    OnBoardingScreen(
                        event = {
                            viewModel::onEvent // ViewModel의 onEvent 함수로 이벤트 전달.
                        }
                    )
                }
            }
        }
    }
}