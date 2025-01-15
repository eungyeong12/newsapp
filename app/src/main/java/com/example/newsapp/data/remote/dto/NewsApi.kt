package com.example.newsapp.data.remote.dto

import com.example.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

// 뉴스 데이터를 가져오기 위해 외부 API와 통신하는 인터페이스
interface NewsApi {

    // 뉴스 데이터를 가져오는 함수
    @GET("everything") // HTTP GET 요청을 "everything" 경로로 보냄
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse // API 호출 결과로 반환되는 데이터 형식
}