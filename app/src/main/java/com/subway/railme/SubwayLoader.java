package com.subway.railme;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SubwayLoader extends AsyncTaskLoader<String> {

    private final DatabaseReference databaseReference;

    public SubwayLoader(@NonNull Context context) {
        super(context);
        this.databaseReference = FirebaseDatabase.getInstance().getReference("subwayData");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        try {
            // 네트워크 요청 수행 코드
            URL url = new URL("https://openapi.kric.go.kr/openapi/trainUseInfo/subwayRouteInfo?serviceKey=YOUR_API_KEY&format=json&mreaWideCd=01&lnCd=A1");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Firebase에 데이터 저장
                saveDataToFirebase(result.toString());

                return result.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void saveDataToFirebase(String data) {
        try {
            // JSON 데이터 파싱 및 Firebase에 저장
            JSONArray lines = new JSONArray(data);

            for (int i = 0; i < lines.length(); i++) {
                JSONObject lineJson = lines.getJSONObject(i);

              //임시
                String lineCode = lineJson.getString("lnCd");
                String areaCode = lineJson.getString("mreaWideCd");
                String operationCode = lineJson.getString("railOprIsttCd");
                String routeCode = lineJson.getString("routCd");
                String routeName = lineJson.getString("routNm");

                // Firebase에 데이터 저장
                DatabaseReference lineRef = databaseReference.child("lines").child(lineCode);
                lineRef.child("areaCode").setValue(areaCode);
                lineRef.child("operationCode").setValue(operationCode);
                lineRef.child("routeCode").setValue(routeCode);
                lineRef.child("routeName").setValue(routeName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
