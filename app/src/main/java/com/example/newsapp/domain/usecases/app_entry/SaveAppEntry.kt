package com.example.newsapp.domain.usecases.app_entry

import com.example.newsapp.domain.manger.LocalUserManger

// LocalUserManger를 통해 앱의 첫 진입 상태를 저장하는 기능을 제공한다.
class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {

    // invoke 연산자 함수를 사용하여 객체를 함수처럼 호출 가능
    // suspend 키워드가 붙어 있어 비동기적으로 실행
    suspend operator fun invoke() {
        // LocalUserManger의 saveAppEntry()를 호출하여 앱 첫 진입 상태를 저장
        localUserManger.saveAppEntry()
    }
}