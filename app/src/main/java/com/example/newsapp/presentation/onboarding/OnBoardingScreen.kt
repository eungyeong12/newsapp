package com.example.newsapp.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.Dimens.MediumPadding2
import com.example.newsapp.presentation.Dimens.PageIndicatorWidth
import com.example.newsapp.presentation.common.NewsButton
import com.example.newsapp.presentation.common.NewsTextButton
import com.example.newsapp.presentation.onboarding.components.OnBoardingPage
import com.example.newsapp.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit // OnBoardingEvent를 처리하는 람다 함수. ViewModel에서 이 함수를 받아 처리.
) {
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
        Spacer(modifier = Modifier.weight(1f));
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding2)
            .navigationBarsPadding(), // 기기의 navigationBar 영역을 고려하여 패딩 추가
            horizontalArrangement = Arrangement.SpaceBetween, // Row 내의 요소들을 좌우로 균등하게 배치
            verticalAlignment = Alignment.CenterVertically // Row 내의 요소들을 세로 중앙에 정렬
        ) {
            // PageIndicator를 Row의 자식으로 추가
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                val scope = rememberCoroutineScope()
                // Composable 내부에서 코루틴을 수행할 경우 Composable에 대한 Recomposition이 일어날 때
                // 정리되어야 하는 Coroutine이 정리가 안된 상태로 계속 Coroutine이 쌓일 수 있다.
                // rememberCoroutineScope은 Composable의 Lifecycle을 따르는 CoroutineScope를 제공한다.

                // '이전' 버튼 렌더링
                if (buttonState.value[0].isNotEmpty()) { // buttonState 값이 비어 있지 않은 경우
                    NewsTextButton(
                        text = buttonState.value[0], // 버튼 텍스트 설정
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                                // 현재 페이지에서 이전 페이지로 스크롤
                                // 코루틴 없이 실행하면, 애니메이션이 완료될 때까지 UI 스레드가 차단될 수 있다.
                            }
                        }
                    )
                }

                // '다음' 또는 '시작하기' 버튼 렌더링
                NewsButton(
                    text = buttonState.value[1], // 버튼 텍스트 설정
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                // SaveAppEntry 이벤트를 호출하여 사용자의 OnBoarding 완료 상태를 저장하도록 요청
                                event(OnBoardingEvent.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                    // 현재 페이지에서 다음 페이지로 스크롤
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}