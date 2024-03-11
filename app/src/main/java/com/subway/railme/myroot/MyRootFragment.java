package com.subway.railme.myroot;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subway.railme.R;
import com.subway.railme.databinding.FragmentMyPageBinding;
import com.subway.railme.databinding.FragmentMyRootBinding;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MyRootFragment extends Fragment {

    private FragmentMyRootBinding binding_r;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding_r = FragmentMyRootBinding.inflate(inflater, container, false);
        return binding_r.getRoot();

        binding_r.btFindRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AssetManager assetManager = getContext().getAssets();

                try {
                    InputStream inputStream = assetManager.open();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuffer buffer = new StringBuffer();
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        buffer.append(line + "\n");
                        line = bufferedReader.readLine();
                    }
                    String jsonData = buffer.toString();

                    //   binding_r.tvFindResult.setText("출발역: " + startName + "\n" + "도착역: " + endName + "\n" + "총 역 수: " + stationCount + "개 역 경유\n" + "총 시간: " + travelTime + "분 소요\n" + "빠른 환승: " + fastDoor + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding_r = null;
    }
}

//일단 이부분 혜림이가 하던거랑 합치기가 좀 애매해서 일단 주석처리하고 잠시 따로 빼놧습니다
/*
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    private EditText etDepartureTime;
    private EditText etDeparture;
    private EditText etDestination;
    private TextView tvFindResult;
    private Button btFindRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDepartureTime = findViewById(R.id.et_StarringTime);
        etDeparture = findViewById(R.id.et_Departure);
        etDestination = findViewById(R.id.et_Destination);
        tvFindResult = findViewById(R.id.tv_FindResult);
        btFindRoot = findViewById(R.id.bt_FindRoot);
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

*/