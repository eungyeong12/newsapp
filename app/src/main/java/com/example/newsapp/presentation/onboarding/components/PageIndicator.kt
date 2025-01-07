package com.example.newsapp.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.newsapp.presentation.Dimens.IndicatorSize
import com.example.newsapp.ui.theme.BlueGray

@Composable
fun PageIndicator( // 페이지 기반 UI에서 현재 페이지를 표시하기 위해 사용
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = BlueGray
) {
    // 가로로 배치
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween // Indicator를 균등하게 배치
    ) {
        // 페이지 수만큼 반복하여 indicator 생성
        repeat(pageSize) { page ->
            // Box는 하나의 indicator를 나타냄
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}