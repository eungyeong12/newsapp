package com.example.newsapp.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.example.newsapp.presentation.onboarding.components.OnBoardingPage

@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // 페이지 상태를 관리
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size // 총 페이지 수 설정
        }

        // 버튼 상태를 관리하는 derivedStateOf를 사용하여 현재 페이지에 따라 버튼 텍스트를 설정
        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage) {
                    0 -> listOf("", "다음")
                    1 -> listOf("이전", "다음")
                    2 -> listOf("이전", "시작하기")
                    else -> listOf("", "")
                }
            }
        }

        // 좌우로 스크롤 가능한 페이지를 생성
        HorizontalPager(state = pagerState) { index ->
            // 각 페이지에 해당하는 OnBoardingPage를 렌더링
            OnBoardingPage(page = pages[index])
        }
    }
}