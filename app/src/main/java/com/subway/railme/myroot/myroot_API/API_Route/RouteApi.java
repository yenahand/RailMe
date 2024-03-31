
package com.subway.railme.myroot.myroot_API.API_Route;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RouteApi{
    @GET("subwayPath")
    Call<RouteResponse> getSubwayPath(
            @Query("apiKey") String apiKey,
            @Query("SID") int startStationID, //출발역
            @Query("EID") int endStationID,  //도착역
            @Query("lang") int lang,   //언어 (한국어:0)
            @Query("output") String output, //출력포맷 (json)
            @Query("CID") int CID, //도시코드(수도권:1000)
            @Query("Sopt") int Sopt  // (1:최단경로/2.최소환승)
    );
}

