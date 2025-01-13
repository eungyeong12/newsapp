package com.example.newsapp.presentation.onboarding

// OnBoarding 화면에서 발생할 수 있는 이벤트들을 정의
sealed class OnBoardingEvent {

    // 사용자가 앱에 처음 진입했음을 저장
    object SaveAppEntry: OnBoardingEvent()
}