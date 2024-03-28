package com.subway.railme.myroot.myroot_API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/subwayPath")
    Call<ApiResponse> getSubwayPath(
            @Query("lang") int lang,
            @Query("CID") int CID,
            @Query("Sopt") int Sopt,
            @Query("apiKey") String apiKey,
            @Query("startStationID") String startStationID,
            @Query("endStationID") String endStationID,
            @Query("departureTime") String departureTime
    );
}
