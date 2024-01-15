//Retrofit을 사용한 API 매니저 클래스
package com.subway.railme.home.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
//레트로핏 통신을 패키지로 분리 해놓았는데 그냥 이렇게 하려고 하시는거죠?
public class ApiManager {
    private static Retrofit retrofit;
    private static Gson gson;

    public interface SubwayApiService {
        @GET("/subway/realtimeStationArrival/1/json")
        Call<ApiResponseModel> getRealtimeArrivalInfo(@Query("apiKey") String apiKey, @Query("stationName") String stationName);
    }

    // Retrofit 인스턴스 초기화
    public static Retrofit getRetrofit() {
        gson = new GsonBuilder().create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://swopenapi.seoul.go.kr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // Retrofit 인스턴스를 사용하여 서비스 인터페이스 생성
    public static SubwayApiService getSubwayApiService() {
        return getRetrofit().create(SubwayApiService.class);
    }

    // 지하철 도착 정보
    public static Call<ApiResponseModel> getRealtimeArrivalInfo(String stationName) {
        SubwayApiService subwayApiService = getSubwayApiService();
        return subwayApiService.getRealtimeArrivalInfo(getApiKey(), stationName);
    }

    // API 키
    public static String getApiKey() {
        return "63686678677470663633696757744c";
    }
}
