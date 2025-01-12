package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.domain.usecases.AppEntryUseCases
import com.example.newsapp.presentation.onboarding.OnBoardingScreen
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint // Hilt를 사용하여 의존성 주입을 가능하게 설정
class MainActivity : AppCompatActivity() {
    @Inject // Hilt을 통해 주입받을 AppEntryUseCases 객체
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen() // 앱 시작 시 스플래시 화면 표시

        lifecycleScope.launch {
            // 앱의 첫 진입 여부를 확인
            appEntryUseCases.readAppEntry().collect {
                Log.d("Test", it.toString())
            }
        }

        setContent {
            NewsAppTheme {
                // Box는 ui 요소를 감싸는 컨테이너
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background))
                // OnBoarding 화면 표시
                OnBoardingScreen()
            }
        }
    }
}