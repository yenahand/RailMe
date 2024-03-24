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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.subway.railme.R;
import com.subway.railme.myroot.myroot_API.OdsayApiKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
                onSearchButtonClick(v);
            }
        });

        return rootView;
    }

    public void onSearchButtonClick(View view) {
        String departureTime = etDepartureTime.getText().toString();
        String departureStation = etDeparture.getText().toString();
        String destinationStation = etDestination.getText().toString();

        // Odyssey API 호출
        new OdysseyApiTask().execute(departureTime, departureStation, destinationStation);
    }

    private class OdysseyApiTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String departureTime = params[0];
            String departureStation = params[1];
            String destinationStation = params[2];
            String apiKey = OdsayApiKey.key;

            try {
                // Odyssey API 호출 및 응답 받기
                URL url = new URL("https://api.odsay.com/v1/api/subwayPath?lang=0&CID=1000&Sopt=1" +
                        "&apiKey=" + URLEncoder.encode(apiKey, "UTF-8") +
                        "&startStationID=" + URLEncoder.encode(departureStation, "UTF-8") +
                        "&endStationID=" + URLEncoder.encode(destinationStation, "UTF-8") +
                        "&departureTime=" + URLEncoder.encode(departureTime, "UTF-8"));
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                // 최단경로를 텍스트로 표시하는 메소드 호출
                displayShortestPath(result);
            } else {
                tvFindResult.setText("API 호출에 실패했습니다.");
            }
        }
    }

    private void displayShortestPath(String apiResponse) {
        try {
            // API 응답 데이터에서 최단 경로 정보 추출
            JSONObject jsonResponse = new JSONObject(apiResponse);
            JSONArray pathArray = jsonResponse.getJSONArray("subPath");

            // 최단 경로를 텍스트로 변환
            StringBuilder pathText = new StringBuilder();
            for (int i = 0; i < pathArray.length(); i++) {
                JSONObject pathStep = pathArray.getJSONObject(i);
                String stationName = pathStep.getString("stationName");
                String transferInfo = pathStep.optString("way", "");

                if (i > 0) {
                    pathText.append(" --> ");
                }

                pathText.append(stationName);
                if (!transferInfo.isEmpty()) {
                    pathText.append(" (").append(transferInfo).append(")");
                }
            }

            // 총 소요시간
            String totalTime = jsonResponse.optString("totalTime", "");
            pathText.append("\n\n총 소요시간: ").append(totalTime);

            // 텍스트뷰에 최단 경로 표시
            tvFindResult.setText(pathText.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            tvFindResult.setText("최단 경로 표시에 실패했습니다.");
        }
    }
}