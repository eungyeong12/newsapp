package com.example.newsapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = Black,
    primary = Blue,
    error = DarkRed,
    surface = LightBlack
)

private val LightColorScheme = lightColorScheme(
    background = Color.White,
    primary = Blue,
    error = LightRed,
    surface = Color.White
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // 기본적으로 시스템 다크 모드 상태에 따라 결정
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // dynamicColor가 활성화되어 있고 Android 12 이상인 경우
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current // 현재 context 가져오기
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current // 현재 뷰 가져오기
    if (!view.isInEditMode) { // 에디터 모드가 아닐 떄만 실행
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb() // 상태바 색상을 primary 색상으로 설정
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme // 상태바 아이콘 색상 변경
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // 액의 전역 텍스트 스타일
        content = content // 테마가 적용될 콘텐츠
    )
}