
package com.subway.railme.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.GestureDetectorCompat;
import com.subway.railme.R;
import com.subway.railme.home.MetroApiManager;
import com.subway.railme.home.MetroApiService;
import com.subway.railme.home.MetroStationInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends AppCompatActivity {

    private ImageViewZoomable imageViewZoomable;
    private AppCompatEditText searchStation;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetectorCompat gestureDetector;
    private ListView stationListView;
    private ArrayAdapter<String> stationAdapter;
    private List<MetroStationInfo> stationList;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        imageViewZoomable = findViewById(R.id.iv_SubwayMap);
        searchStation = findViewById(R.id.searchStation);
        stationListView = findViewById(R.id.stationListView);

        stationList = new ArrayList<>();
        stationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        stationListView.setAdapter(stationAdapter);

        // API 연동
        // callSubwayApi();

        // 줌인/줌아웃 및 스크롤 기능 설정
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        imageViewZoomable.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            gestureDetector.onTouchEvent(event);
            return true;
        });

        // 상하좌우 스크롤 기능 설정
        gestureDetector = new GestureDetectorCompat(this, new ScrollListener());

        searchStation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // 검색어가 변경될 때마다 역 정보를 검색
                searchMetroStation(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    //api키
    public static class ApiKeyManager {
        private static ApiKeyManager BuildConfig;
        public static final String API_KEY = BuildConfig.API_KEY;
    }

    private void searchMetroStation(String query) {
        MetroApiService apiService = MetroApiManager.getApiService();
        Call<List<MetroStationInfo>> call = apiService.getMetroStationInfo(query);

        call.enqueue(new Callback<List<MetroStationInfo>>() {
            @Override
            public void onResponse(Call<List<MetroStationInfo>> call, Response<List<MetroStationInfo>> response) {
                if (response.isSuccessful()) {
                    stationList.clear();
                    stationList.addAll(response.body());

                    // 검색 결과를 리스트뷰에 업데이트
                    updateStationListView();
                } else {
                    Toast.makeText(HomeFragment.this, "검색에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MetroStationInfo>> call, Throwable t) {
                Toast.makeText(HomeFragment.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStationListView() {
        stationAdapter.clear();
        for (MetroStationInfo station : stationList) {
            stationAdapter.add(station.getName());
        }
        stationAdapter.notifyDataSetChanged();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            float currentScale = imageViewZoomable.getScaleX();
            float newScale = currentScale * scaleFactor;
            newScale = Math.max(0.1f, Math.min(newScale, 5.0f));
            imageViewZoomable.setScaleX(newScale);
            imageViewZoomable.setScaleY(newScale);
            return true;
        }
    }

    private class ScrollListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float currentTranslationX = imageViewZoomable.getTranslationX();
            float currentTranslationY = imageViewZoomable.getTranslationY();
            imageViewZoomable.setTranslationX(currentTranslationX - distanceX);
            imageViewZoomable.setTranslationY(currentTranslationY - distanceY);
            return true;
        }
    }
}
