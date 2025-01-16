package com.example.newsapp.di

import android.app.Application
import com.example.newsapp.data.manger.LocalUserMangerImpl
import com.example.newsapp.data.remote.dto.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manger.LocalUserManger
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.newsapp.domain.usecases.news.GetNews
import com.example.newsapp.domain.usecases.news.NewsUseCases
import com.example.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Hilt을 사용하여 의존성 주입을 설정하는 모듈
@Module // Hilt 모듈임을 나타내는 어노테이션
@InstallIn(SingletonComponent::class) // SingletonComponent에 의존성을 주입. 앱 전체에서 동일한 인스턴스를 사용.
object AppModule {

    // LocalUserMager를 제공하는 함수
    @Provides
    @Singleton // Singleton 범위로 관리. 앱을 실행하는 동안 동일한 인스턴스가 유지됨
    fun provideLocalUserManger(
        application: Application // Hilt이 자동으로 주입
    ): LocalUserManger = LocalUserMangerImpl(application) // LocalUserManger의 구현체를 반환

    // AppEntryUseCases를 제공하는 함수
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger // LocalUserManger는 위의 provideLocalUserManger로 생성된 인스턴스를 주입받음.
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger), // 앱 첫 진입 여부를 읽는 Use Case 생성.
        saveAppEntry = SaveAppEntry(localUserManger)// 앱 첫 진입 여부를 저장하는 Use Case 생성.
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }
}