package com.subway.railme.myroot;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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

public class MyRootFragment extends AppCompatActivity {

    private EditText etDepartureTime;
    private EditText etDeparture;
    private EditText etDestination;
    private TextView tvFindResult;
    private Button btFindRoot;

    @SuppressLint("MissingInflatedId")
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

//혜림이꺼 일단 주석처리 해두고 제부분 하고있습니다
/*
import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding_r = null;
    }

    private final ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
            result -> {
        Boolean fineLocationGranted = result.get(Manifest.permission.ACCESS_FINE_LOCATION);
        Boolean coarseLocationGranted = result.get(Manifest.permission.ACCESS_COARSE_LOCATION);

        if(fineLocationGranted != null && fineLocationGranted) {
            startLocationUpdates();
        } else if(coarseLocationGranted != null && coarseLocationGranted) {
            startLocationUpdates();
        } else {
            Toast.makeText(this, "Unable to launch app because lacation permissions are denied.", Toast.LENGTH_SHORT).show();
            finish();
        }
            });

    private  void checkLocationPermission() {
        boolean coarseLocationGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        boolean fineLocationGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(!coarseLocationGranted && !fineLocationGranted) {
            locationPermissionRequest.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        } else {
            startLocationUpdates();
        }
    }
}
*/

