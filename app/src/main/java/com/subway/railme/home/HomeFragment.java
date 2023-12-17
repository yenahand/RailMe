package com.subway.railme.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.subway.railme.R;
import com.subway.railme.SubwayLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    private FrameLayout subwayMapContainer;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        subwayMapContainer = rootView.findViewById(R.id.container);

        databaseReference = FirebaseDatabase.getInstance().getReference("subwayData");

        //API키 발급받으면 넣기
        String apiKey = "API_KEY";
        String apiUrl = "https://openapi.kric.go.kr/openapi/trainUseInfo/subwayRouteInfo?serviceKey=" + apiKey + "&format=json&mreaWideCd=01&lnCd=A1";

        getLoaderManager().initLoader(0, null, this).forceLoad();

        return rootView;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new SubwayLoader(requireContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        // Firebase에서 데이터 가져와서 지하철 노선도 그리기
        loadDataAndDrawSubwayRoute(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // 로더 재설정
    }

    private void loadDataAndDrawSubwayRoute(String data) {
        try {
            // JSON 데이터 파싱 및 파이어베이스에 저장
            JSONArray lines = new JSONArray(data);

            for (int i = 0; i < lines.length(); i++) {
                JSONObject lineJson = lines.getJSONObject(i);

                String lineCode = lineJson.getString("lnCd");
                String routeName = lineJson.getString("routNm");

                drawSubwayRoute(lineCode, routeName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void drawSubwayRoute(String lineCode, String routeName) {
        //지하 노선도 그리는 코드

        TextView lineTextView = new TextView(requireContext());
        lineTextView.setText(routeName);
        lineTextView.setTextColor(Color.BLACK);
        subwayMapContainer.addView(lineTextView);

        //1호선
        if ("1001".equals(lineCode)) {
            // 역 표시, 환승 역, 노선 간의 연결 등
            //역 추가:
            addStation("1001", "Seoul Station", "서울역");
            addStation("1001", "Yongsan Station", "용산역");
            // 환승 역 추가
            addTransferStation("1001", "Seoul Station");
            // 노선 간의 연결 추가
            connectLines("1001", "2001"); // 1호선과 2호선 연결
        }

        //추가노선정보 그리는 로직
    }

    private void addStation(String lineCode, String stationCode, String stationName) {
        // 역 추가하는 로직
        // 외않되 도대체

    }

    private void addTransferStation(String lineCode, String stationName) {
        // 환승 역 추가하는 로직
    }

    private void connectLines(String lineCode1, String lineCode2) {
        // 노선 간 연결을 나타내는 로직

    }
}
