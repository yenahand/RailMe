package com.subway.railme.retrofit;
//Retrofit을 사용하여 HTTP 요청을 처리하는 인터페이스 [API 호출에 필요한 메소드 정의] 여기다가 옮길게요
import com.subway.railme.home.ApiResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SubwayApi {
    @GET("trainUseInfo/subwayRouteInfo")
    Call<ApiResponseModel> getSubwayRouteInfo(
            @Query("serviceKey") String serviceKey,
            @Query("format") String format,
            @Query("lnCd") String lnCd,
            @Query("mreaWideCd") String mreaWideCd
    );
}
