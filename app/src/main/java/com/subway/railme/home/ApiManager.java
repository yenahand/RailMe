//Retrofit을 초기화하고 API Interface를 생성하는 클래스
package com.subway.railme.home;
import com.subway.railme.retrofit.SubwayApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = "http://swopenapi.seoul.go.kr/api/subway/744144704a74706635304c4d6b644e/xml/realtimeStationArrival/ALL";

    public static SubwayApi createApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SubwayApi.class);
    }
}