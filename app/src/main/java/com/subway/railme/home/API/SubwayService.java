package com.subway.railme.home.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SubwayService {
    @GET("api/subway/sample/xml/realtimeStationArrival/0/5/{stationName}")
    Call<RealtimeStationArrivalResponse> getRealtimeStationArrival(
            @Path("stationName") String stationName,
            @Query("apiKey") String apiKey
    );
}