package com.subway.railme.home.subwayapi

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofitInstances: MutableMap<String, Retrofit> = mutableMapOf()

    fun getRetrofitInstance(baseUrl: String, apiKey: String): Retrofit {
        return retrofitInstances["$baseUrl-$apiKey"] ?: buildRetrofitInstance(
            baseUrl,
            apiKey
        ).also {
            retrofitInstances["$baseUrl-$apiKey"] = it
        }
    }

    private fun buildRetrofitInstance(baseUrl: String, apiKey: String): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 로그 수준 설정 (NONE, BASIC, HEADERS, BODY)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor) // 로깅 인터셉터 추가
            .addInterceptor { chain ->
                val request: Request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", apiKey) // API 키 추가
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getApi(baseUrl: String, apiKey: String): RetrofitInterface {
        return getRetrofitInstance(baseUrl, apiKey).create(RetrofitInterface::class.java)
    }
}