package com.subway.railme.home.API;

import com.subway.railme.home.API.remote.RealtimeStationArrivalResponse;
import com.tickaroo.tikxml.annotation.Path;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SubwayService {
    @GET("api/subway/{apiKey}/xml/realtimeStationArrival/0/1/{stationName}")
    Call<RealtimeStationArrivalResponse> getRealtimeStationArrival(
            @Path("apiKey") String apiKey,
            @Path("stationName") String stationName
    );
}