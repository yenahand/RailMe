/*
package com.subway.railme.myroot.myroot_API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitManager {

    public void getSubwayPath(String departureTime, String departureStation, String destinationStation, final MyApiCallback callback) {
        ApiService service = APIRetrofit.getClient().create(ApiService.class);

        Call<ApiResponse> call = service.getSubwayPath(
                0, // lang (언어: 한국어)
                1000, // CID (도시코드:수도권)
                1, // Sopt (최단거리)
                OdsayApiKey.key, // api키
                departureStation, // 출발역ID
                destinationStation, //도착역ID
                departureTime //소요시간
        );

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    callback.onSuccess(apiResponse);
                } else {
                    callback.onError("API 호출 실패...");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public interface MyApiCallback {
        void onSuccess(ApiResponse response);
        void onError(String errorMessage);
    }
}
*/
