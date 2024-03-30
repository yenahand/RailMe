package com.subway.railme.home.subwayapi

import com.subway.railme.home.domain.dto.RealtimeStationArrival
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("{apiKey}/json/realtimeStationArrival/{startIndex}/{endIndex}/{statnNm}")
    suspend fun getSubway(
        @Path("apiKey") apiKey: String,
        @Path("startIndex") startIndex: Int = 0,
        @Path("endIndex") endIndex: Int = 2, // 페이지 수
        @Path("statnNm") stationName: String,
        @Query("TYPE") type: String = "json",
        @Query("SERVICE") service: String = "realtimeStationArrival"
    ): Response<RealtimeStationArrival>
}