package com.example.newsapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsapp.presentation.onboarding.OnBoardingScreen
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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