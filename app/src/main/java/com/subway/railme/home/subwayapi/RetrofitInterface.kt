package com.subway.railme.home.subwayapi

import com.subway.railme.home.API.dto.RealtimeStationArrival
import com.tickaroo.tikxml.annotation.Path
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("subway/{key}/{type}/realtimeStationArrival/{startIndex}/{endIndex}/{stationName}")
    suspend fun getSubway(
        @Path("key") key: String,
        @Path("type") type: String,
        @Path("startIndex") startIndex: Int,
        @Path("endIndex") endIndex: Int,
        @Path("stationName") stationName: String
    ): Response<RealtimeStationArrival>
}