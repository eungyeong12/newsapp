package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

// LocalUserManger를 통해 앱의 첫 진입 여부를 읽는 기능을 제공한다.
class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {

    // invoke 연산자 함수를 사용하여 객체를 함수처럼 호출 가능
    // suspend 키워드가 붙어 있어 비동기적으로 실행
    suspend operator fun invoke(): Flow<Boolean>{
        // LocalUserManger의 readAppEntry()를 호출하여 데이터 반환
        return localUserManger.readAppEntry()
    }
}