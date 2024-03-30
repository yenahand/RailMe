package com.subway.railme.home.subwayapi

import com.subway.railme.unit.Unit.API
import com.subway.railme.unit.Unit.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: RetrofitInterface get() = instance.create(RetrofitInterface::class.java)

    private val instance: Retrofit
        get() {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // 로그 수준 설정 (NONE, BASIC, HEADERS, BODY)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor) // 로깅 인터셉터 추가
                .addInterceptor { chain ->
                    val request: Request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", API)
                        .build()
                    chain.proceed(request)
                }
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

}