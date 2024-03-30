/*
package com.subway.railme.myroot;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.subway.railme.R;
import com.subway.railme.myroot.myroot_API.ApiResponse;
import com.subway.railme.myroot.myroot_API.ApiService;
import com.subway.railme.myroot.myroot_API.APIRetrofit;
import com.subway.railme.myroot.myroot_API.OdsayApiKey;
import com.subway.railme.myroot.myroot_API.SubPath;

import java.io.IOException;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRootFragment extends Fragment {

    private EditText etDepartureTime;
    private EditText etDeparture;
    private EditText etDestination;
    private TextView tvFindResult;
    private Button btFindRoot;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_root, container, false);

        etDepartureTime = rootView.findViewById(R.id.et_StarringTime);
        etDeparture = rootView.findViewById(R.id.et_Departure);
        etDestination = rootView.findViewById(R.id.et_Destination);
        tvFindResult = rootView.findViewById(R.id.tv_FindResult);
        btFindRoot = rootView.findViewById(R.id.bt_FindRoot);

        btFindRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick();
            }
        });

        return rootView;
    }

    private void onSearchButtonClick() {
        String departureTime = etDepartureTime.getText().toString();
        String departureStation = etDeparture.getText().toString();
        String destinationStation = etDestination.getText().toString();

        // Odyssey API 호출
        new OdysseyApiTask().execute(departureTime, departureStation, destinationStation);
    }

    private class OdysseyApiTask extends AsyncTask<String, Void, ApiResponse> {

        @Override
        protected ApiResponse doInBackground(String... params) {
            String departureTime = params[0];
            String departureStation = params[1];
            String destinationStation = params[2];
            String apiKey = OdsayApiKey.key;

            try {
                ApiService service = APIRetrofit.getClient().create(ApiService.class);

                Call<ApiResponse> call = service.getSubwayPath(
                        0, // lang (언어: 한국어)
                        1000, // CID (도시코드:수도권)
                        1, // Sopt (1로하면 최단거리,2로하면 최소환승입니다)
                        apiKey, // api키
                        departureStation, // 출발역ID
                        destinationStation, //도착역ID
                        departureTime //소요시간
                );

                Response<ApiResponse> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ApiResponse result) {
            if (result != null) {
                displayShortestPath(result);
            } else {
                tvFindResult.setText("API 호출 실패 ｡°(°´ᯅ`°)°｡");
            }
        }
    }

    //텍스트뷰에 길찾기 환승정보 표시 (최단거리 기준)
    private void displayShortestPath(ApiResponse apiResponse) {
        SubPath[] subPaths = apiResponse.getSubPaths();

        if (subPaths != null && subPaths.length > 0) {
            StringBuilder pathText = new StringBuilder();
            for (int i = 0; i < subPaths.length; i++) {
                SubPath pathStep = subPaths[i];
                String stationName = pathStep.getStationName();
                String transferInfo = pathStep.getWay();

                if (i > 0) {
                    pathText.append(" --> ");
                }

                pathText.append(stationName);
                if (transferInfo != null && !transferInfo.isEmpty()) {
                    pathText.append(" (").append(transferInfo).append(")");
                }
            }

            String totalTime = apiResponse.getTotalTime();
            pathText.append("\n\n총 소요시간: ").append(totalTime);

            tvFindResult.setText(pathText.toString());
        } else {
            tvFindResult.setText("최단경로 정보가 없습니다...");
        }
    }
}
*/
