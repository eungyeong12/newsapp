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
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

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

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
    }
}