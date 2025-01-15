package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

// 뉴스 데이터를 관리하고 제공하는 역할을 하는 인터페이스
interface NewsRepository {

    // 뉴스 데이터를 가져오는 함수
    // 페이징된 뉴스 데이터를 Flow로 반환
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}