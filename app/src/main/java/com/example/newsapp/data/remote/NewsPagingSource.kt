package com.example.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.dto.NewsApi
import com.example.newsapp.domain.model.Article

// Android Paging 라이브러리를 사용해 뉴스 데이터를 페이징 처리
class NewsPagingSource(
    private val newsApi: NewsApi, // 뉴스 데이터를 가져올 API 인터페이스
    private val sources: String
): PagingSource<Int, Article>() { // Int: 페이지 키 타입, Article: 반환할 데이터 타입

    private var totalNewsCount = 0 // 현재까지 로드된 뉴스 기사의 총 개수

    // API를 호출하여 데이터를 로드하는 함수
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1 // 요청할 페이지 키, 기본값은 1
        return try {
            // 뉴스 API 호출
            val newsResponse = newsApi.getNews(sources = sources, page = page)

            // 로드된 뉴스 기사 수 누적
            totalNewsCount += newsResponse.articles.size

            // 중복된 기사를 제거 (제목 기준)
            val articles = newsResponse.articles.distinctBy { it.title }

            // 성공적으로 로드된 데이터를 반환
            LoadResult.Page(
                data = articles, // 로드된 데이터
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1, // 다음 페이지 키
                prevKey = null // 이전 페이지 키 (첫 페이지이므로 null)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            // 예외 발생 시 오류 결과 반환
            LoadResult.Error(
                throwable = e
            )
        }
    }

    // 페이징 데이터를 새로고침할 때 기준이 되는 키를 반환하는 함수
    // state PagingState - 현재 페이징 상태
    // Int? - 새로고침할 페이지 키
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition -> // 현재 스크롤 위치의 Anchor를 가져옴
            val anchorPage = state.closestPageToPosition(anchorPosition) // Anchor에 가장 가까운 페이지
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1) // 새로고침할 페이지 키 계산
        }
    }
}