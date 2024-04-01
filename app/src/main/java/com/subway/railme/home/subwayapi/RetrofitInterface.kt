package com.subway.railme.home.subwayapi

import com.subway.railme.home.domain.dto.RealtimeStationArrival
import com.subway.railme.myroot.dto.TrasitDto
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
    ): Response<RealtimeStationArrival>

    @GET("{api}/searchStation/{lang}/{stationName}")
    suspend fun getStation(
        @Query("lang") language: Int = 0, // (0: 국문, 1: 영문, 2: 일문, 3: 중문(간체), 4: 중문(번체), 5: 베트남어)
        @Query("stationName") stationName: String, // 정류장을 검색할 이름 (2자 이상)
        @Query("CID") cityCode: Int? = null,
        @Query("stationClass") stationClass: String? = null,
        @Query("displayCnt") displayCount: Int = 1, // 리턴 결과 개수 (선택적)
        @Query("startNO") startNumber: Int = 1, // 결과 개수 중 시작 번호 (선택적)
        @Query("myLocation") myLocation: String? = null
    ):Response<TrasitDto>
}