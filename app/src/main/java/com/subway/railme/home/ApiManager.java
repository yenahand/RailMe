//Retrofit을 초기화하고 API Interface를 생성하는 클래스
package com.subway.railme.home;
import com.subway.railme.retrofit.SubwayApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = "https://openapi.kric.go.kr/openapi/";

    public static SubwayApi createApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SubwayApi.class);
    }
}