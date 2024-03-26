package com.subway.railme.home.API.remote;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.subway.railme.home.API.SubwayService;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

public class SearchTask extends AsyncTask<Void, Void, String> {
    private static final String BASE_URL = "https://swopenapi.seoul.go.kr/";
    private static final String API_KEY = "59436b514a74706633314b69617558";
    private TextView textView;
    private SearchTaskListener listener;

    public SearchTask(TextView textView, SearchTaskListener listener) {
        this.textView = textView;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        TikXml parser = new TikXml.Builder().exceptionOnUnreadXml(false).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(TikXmlConverterFactory.create(parser))
                .build();

        SubwayService service = retrofit.create(SubwayService.class);
        Call<RealtimeStationArrivalResponse> call = service.getRealtimeStationArrival(API_KEY, "stationName");

        try {
            Response<RealtimeStationArrivalResponse> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                RealtimeStationArrivalResponse arrivalResponse = response.body();
                List<RealtimeStationArrival> arrivals = arrivalResponse.getRealtimeArrivalList();
                StringBuilder result = new StringBuilder();
                for (RealtimeStationArrival arrival : arrivals) {
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
        if (listener != null) {
            listener.onSearchTaskComplete(result);
        }
    }

    private TikXml buildTikXml() {
        return new TikXml.Builder()
                .exceptionOnUnreadXml(false)
                .build();
    }

    public interface SearchTaskListener {
        void onSearchTaskComplete(String result);
    }
}