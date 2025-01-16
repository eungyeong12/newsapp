package com.example.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// 홈 화면에서 사용할 데이터를 관리하는 ViewModel
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases // 뉴스 관련 비즈니스 로직을 처리하는 Use Case를 주입받음
): ViewModel() {

    // 뉴스 데이터를 가져오는 Flow
    // NewsUseCases의 getNews를 호출하여 뉴스 데이터를 가져옴
    // Flow를 ViewModelScope에서 캐싱하여 효율적으로 데이터 관리
    val news = newsUseCases.getNews(
        sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope) // Paging 데이터를 ViewModelScope에서 캐싱하여 화면 회전 또는 UI 재구성 시 데이터를 유지
}