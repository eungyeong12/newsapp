package com.example.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.AppEntryUseCases
import com.example.newsapp.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// 앱의 초기 화면 설정과 스플래시 화면 상태를 관리하는 ViewModel
// Hilt를 사용하여 의존성을 주입받아 UseCase를 통해 데이터를 처리
@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases // 앱 첫 진입 여부를 처리하는 UseCase 모음
): ViewModel() {

    // 스플래시 화면 표시 여부를 나타내는 상태 변수
    // true일 경우 스플래시 화면이 표시되며, false가 되면 앱 메인 화면으로 전환
    var splashCondition by mutableStateOf(true)
        private set

    // 네비게이션의 시작 경로를 저장하는 상태 변수
    // 초기에는 AppStartNavigation 경로를 사용
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        // 앱 진입 여부를 확인하기 위해 UseCase 호출
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            // UseCase 결과에 따라 시작 화면 경로 설정
            if (shouldStartFromHomeScreen) {
                // 이전에 OnBoarding을 완료한 경우, 홈 화면으로 시작
                startDestination = Route.NewsNavigation.route
            } else {
                // OnBoarding을 완료하지 않은 경우, OnBoarding 화면으로 시작
                startDestination = Route.AppStartNavigation.route
            }
            // 스플래시 화면을 300ms 동안 표시
            delay(300)
            splashCondition = false // 스플래시 화면 종료
        }.launchIn(viewModelScope) // ViewModelScope 내에서 Coroutine 실행
    }
}