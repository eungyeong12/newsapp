package com.example.newsapp.domain.usecases

// 앱의 진입 관련 Use Case를 묶어서 관리
data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry, // 앱 첫 진입 여부를 읽는 Use Case
    val saveAppEntry: SaveAppEntry // 앱 첫 진입 여부를 저장하는 Use Case
)
