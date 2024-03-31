package com.subway.railme.myroot.myroot_API.API_StationID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StationIDApi {
    @GET("searchStation")
    Call<StationIDResponse> searchStation(
            @Query("apiKey") String apiKey,
            @Query("stationName") String stationName,
            @Query("output") String output
    );
}

