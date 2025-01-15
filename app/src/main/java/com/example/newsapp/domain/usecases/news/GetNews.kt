package com.example.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

// 뉴스 데이터를 가져오는 Use Case
// ViewModel에서 직접 Repository를 호출하는 대신, Use Case를 통해 비즈니스 로직을 캡슐화
// NewsRepository를 호출하여 비즈니스 로직을 처리하고, 필요한 데이터를 반환
class GetNews(
    private val newsRepository: NewsRepository // NewsRepository를 주입받음
) {

    // Invoke 연산자 오버로딩으로 Use Case를 함수처럼 호출할 수 있게 함
    // 페이징된 뉴스 데이터를 Flow로 반환
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}