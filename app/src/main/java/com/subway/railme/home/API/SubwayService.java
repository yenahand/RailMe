package com.subway.railme.home.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SubwayService {
    @GET("api/subway/{apiKey}/xml/realtimeStationArrival/0/1/{stationName}")
    Call<RealtimeStationArrivalResponse> getRealtimeStationArrival(
            @Query("apiKey") String apiKey,
            @Query("stationName") String stationName
    );
}