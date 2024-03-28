package com.subway.railme.home.subwayapi

import com.subway.railme.Unit
import com.subway.railme.Unit.API
import com.subway.railme.Unit.TYPE
import com.subway.railme.home.API.dto.RealtimeStationArrival
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("sample/json/realtimeStationArrival/")
    suspend fun getSubway(
        @Query("KEY") key: String = API,
        @Query("TYPE") TYPE: String = "json",
        @Query("SERVICE") service: String = "realtimeStationArrival",
        @Query("START_INDEX") startIndex: Int = 0,
        @Query("END_INDEX") endIndex: Int = 1,
        @Query("statnNm") stationName: String? = null
    ): Response<RealtimeStationArrival>
}