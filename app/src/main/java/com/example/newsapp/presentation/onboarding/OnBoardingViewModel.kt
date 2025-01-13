package com.example.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// OnBoarding 화면에서 필요한 데이터를 관리하고 로직을 처리하는 ViewModel
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases // AppEntryUseCases 주입
): ViewModel() {

    // OnBoarding 화면에서 발생하는 이벤트를 처리하는 함수
    fun onEvent(event: OnBoardingEvent) {
        when(event) {
            is OnBoardingEvent.SaveAppEntry -> { // SaveAppEntry 이벤트가 발생하면
                saveAppEntry() // 앱 첫 진입 여부를 저장하는 로직 호출
            }
        }
    }

    // 앱 첫 진입 여부를 저장하는 함수
    // 비동기 작업 처리
    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}