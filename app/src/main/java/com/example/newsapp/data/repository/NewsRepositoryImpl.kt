package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.remote.dto.NewsApi
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

// NewsRepository 인터페이스를 구현하는 클래스
// API를 호출하여 뉴스 데이터를 가져오고, Paging 라이브러리를 통해 페이징 데이터를 반환
class NewsRepositoryImpl (
    private val newsApi: NewsApi // 뉴스 데이터를 가져오는 API 인터페이스를 주입받음
): NewsRepository {

    // 뉴스 데이터를 페이징 형태로 반환
    // 페이징된 뉴스 데이터를 Flow 형태로 반환
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10), // 한 페이지당 데이터 개수
            pagingSourceFactory = {
                // NewsPagingSource 생성 및 초기화
                NewsPagingSource(
                    newsApi = newsApi, // 뉴스 API를 전달
                    sources = sources.joinToString(separator = ",") // List<String> 형태의 소스 목록을 콤마(,)로 구분된 문자열로 변환.
                )
            }
        ).flow // Pager의 Flow를 반환
    }
}