package com.example.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Hilt 사용하여 의존성 주입 활성화
class NewsApplication: Application() {
    // Hilt이 내부적으로 DI 컨테이너를 생성하고 애플리케이션 생명 주기와 연결한다.
}