package com.joe.st_unitas.api

import com.joe.st_unitas.data.ImageResponse
import com.joe.st_unitas.kakaoToken
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {
    @GET("v2/search/image")
    fun getImages(
        @Header("Authorization") token: String = kakaoToken,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80,
        @Query("query") query: String
    ): Single<ImageResponse>
}