package com.geek.data.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoAddressApi {
    @GET("v2/local/geo/coord2address.json")
    suspend fun getAddress(
        @Header("Authorization") key: String,
        @Query("x") longitude: Double,
        @Query("y") latitude: Double
    ): AddressResponse
}