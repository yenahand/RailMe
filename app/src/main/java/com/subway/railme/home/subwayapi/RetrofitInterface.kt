package com.subway.railme.home.subwayapi

import com.subway.railme.home.API.dto.RealtimeStationArrival
import com.subway.railme.home.domain.model.ArrivalModel
import com.tickaroo.tikxml.annotation.Path
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("api/subway/{apiKey}/xml/realtimeStationArrival/0/1/{stationName}")
    suspend fun getSubway(
        @Query("key") key: String,
        @Query("type") type: String,
        @Query("startIndex") startIndex: Int,
        @Query("endIndex") endIndex: Int,
        @Query("stationName") stationName: String,
    ): RealtimeStationArrival
}