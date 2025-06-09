package com.geek.data.repository

import android.util.Log
import com.geek.data.remote.KakaoAddressApi
import com.geek.data.remote.NaverSearchApi
import com.geek.data.mapper.CafeMapper
import com.geek.domain.model.Cafe
import com.geek.domain.repository.NearbyCafeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class NearbyCafeRepositoryImpl(
    private val kakaoApi: KakaoAddressApi,
    private val naverApi: NaverSearchApi,
    private val kakaoKey: String,
    private val naverId: String,
    private val naverSecret: String
) : NearbyCafeRepository {
    override suspend fun getNearbyCafes(lat: Double, lon: Double): List<Cafe> = withContext(Dispatchers.IO) {
        try {
            val addressRes = kakaoApi.getAddress("KakaoAK $kakaoKey", lon, lat)
            val region = addressRes.documents.firstOrNull()?.address?.region_1depth_name.orEmpty()
            val cafeRes = naverApi.searchCafes(naverId, naverSecret, "${region} 카페")

            Log.d("test_min", "검색할 현재 좌표 : " + region)

            cafeRes.items.forEach {
                Log.d("test_min", "카페 이름 : " + it.title)
                Log.d("test_min", "주소 : " + it.address)
                Log.d("test_min", "---------------------------")
            }

            cafeRes.items.map(CafeMapper::fromDto)
        } catch (e: HttpException) {
            val errorJson = e.response()?.errorBody()?.string()
            Log.e("test_min", errorJson ?: "no body")
            emptyList()
        }
    }
}