package com.geek.presentation.di

import com.geek.data.BuildConfig
import com.geek.data.remote.KakaoAddressApi
import com.geek.data.remote.NaverSearchApi
import com.geek.data.repository.NearbyCafeRepositoryImpl
import com.geek.domain.repository.NearbyCafeRepository
import com.geek.domain.usecase.GetNearbyCafesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideKakaoApi(): KakaoAddressApi = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KakaoAddressApi::class.java)

    @Provides
    fun provideNaverApi(): NaverSearchApi = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NaverSearchApi::class.java)

    @Provides
    @Singleton
    fun provideNearbyRepo(
        kakaoApi: KakaoAddressApi,
        naverApi: NaverSearchApi
    ): NearbyCafeRepository = NearbyCafeRepositoryImpl(
        kakaoApi, naverApi,
        kakaoKey = BuildConfig.KAKAO_REST_API_KEY,
        naverId = BuildConfig.NAVER_CLIENT_ID,
        naverSecret = BuildConfig.NAVER_CLIENT_SECRET
    )

    @Provides
    fun provideGetNearbyCafesUseCase(repo: NearbyCafeRepository): GetNearbyCafesUseCase =
        GetNearbyCafesUseCase(repo)
}