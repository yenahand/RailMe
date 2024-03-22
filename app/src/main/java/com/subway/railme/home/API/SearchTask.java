package com.subway.railme.home.API;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class SearchTask extends AsyncTask<String, Void, String> {
    private static final String BASE_URL = "https://swopenAPI.seoul.go.kr/";
    private static final String API_KEY = "59436b514a74706633314b69617558";
    private TextView textView;

    public SearchTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... strings) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(TikXmlConverterFactory.create(new TikXml.Builder().exceptionOnUnreadXml(false).build()))
                .client(new OkHttpClient())
                .build();

        SubwayService service = retrofit.create(SubwayService.class);
        Call<RealtimeStationArrivalResponse> call = service.getRealtimeStationArrival(strings[0], API_KEY);

        try {
            Response<RealtimeStationArrivalResponse> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                RealtimeStationArrivalResponse arrivalResponse = response.body();
                StringBuilder result = new StringBuilder();
                for (RealtimeStationArrival arrival : arrivalResponse.getRealtimeArrivalList()) {
                    result.append("지하철역명: ").append(arrival.getStatnNm()).append("\n")
                            .append("상하행선구분: ").append(arrival.getUpdnLine()).append("\n")
                            .append("도착지방면: ").append(arrival.getTrainLineNm()).append("\n")
                            .append("열차도착예정시간: ").append(arrival.getBarvlDt()).append("\n\n");
                }
                Log.d("SearchTask", "API 호출 성공: " + result.toString());
                return result.toString();
            } else {
                String errorMessage = "API 호출 실패: " + response.code();
                Log.e("SearchTask", errorMessage);
                return errorMessage;
            }
        } catch (IOException e) {
            String errorMessage = "API 호출 실패: " + e.getMessage();
            Log.e("SearchTask", errorMessage, e);
            return errorMessage;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
    }
}
