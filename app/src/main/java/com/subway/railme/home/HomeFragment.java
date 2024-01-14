package com.subway.railme.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.SearchView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.GestureDetectorCompat;
import com.subway.railme.R;
import com.subway.railme.home.API.ApiManager;
import com.subway.railme.home.API.ApiResponseModel;
import com.subway.railme.home.API.RealtimeArrival;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends AppCompatActivity {

    private ImageView imageViewZoomable;
    private AppCompatEditText searchStation;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetectorCompat gestureDetector;
    private ListView stationListView;
    private ArrayAdapter<String> stationAdapter;
    private List<RealtimeArrival> stationList;

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

        // 줌인/줌아웃 및 스크롤 기능 설정
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        imageViewZoomable.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            gestureDetector.onTouchEvent(event);
            return true;
        });

        // 상하좌우 스크롤 기능 설정
        gestureDetector = new GestureDetectorCompat(this, new ScrollListener());

        // SearchView의 검색 이벤트 처리
        searchStation.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String stationName = searchStation.getText().toString();
                if (!stationName.isEmpty()) {
                    // 검색된 역에 대한 API 호출
                    callSubwayApi(stationName);
                } else {
                    Toast.makeText(HomeFragment.this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });
    }

    // API 호출 메서드
    private void callSubwayApi(String stationName) {
        Call<ApiResponseModel> call = ApiManager.getRealtimeArrivalInfo(stationName);
        call.enqueue(new Callback<ApiResponseModel>() {
            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {
                if (response.isSuccessful()) {
                    ApiResponseModel apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getRealtimeArrivalList() != null) {
                        stationList = apiResponse.getRealtimeArrivalList();
                        updateStationListView(); // UI 업데이트
                    }
                } else {
                    Toast.makeText(HomeFragment.this, "서버 응답 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseModel> call, Throwable t) {
                Toast.makeText(HomeFragment.this, "통신 실패", Toast.LENGTH_SHORT).show();
            }
        });
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

    // UI 업데이트 메서드
    private void updateStationListView() {
        stationAdapter.clear();
        for (RealtimeArrival station : stationList) {
            stationAdapter.add(station.getStatnNm());
        }
        stationAdapter.notifyDataSetChanged();
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

    // API 키 관리 클래스
    public static class ApiKeyManager {
        public static final String API_KEY = "63686678677470663633696757744c";
    }
}
