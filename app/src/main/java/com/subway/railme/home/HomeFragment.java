//api연동한 부분은 아직 노선도구현을 이미지로 해논 상태여서 일단 주석처리해놨습니다 (매핑하려면 너무 오래걸릴거같아서 다른방법 찾고있어요)

package com.subway.railme.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.GestureDetectorCompat;
import com.subway.railme.R;
import com.subway.railme.home.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends AppCompatActivity {

    private ImageViewZoomable imageViewZoomable;
    private AppCompatEditText searchStation;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetectorCompat gestureDetector;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        imageViewZoomable = findViewById(R.id.iv_SubwayMap);
        searchStation = findViewById(R.id.searchStation);

        // API 연동
        //callSubwayApi();

        // 줌인/줌아웃 및 스크롤 기능 설정
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        imageViewZoomable.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            gestureDetector.onTouchEvent(event);
            return true;
        });

        // 상하좌우 스크롤 기능 설정
        gestureDetector = new GestureDetectorCompat(this, new ScrollListener());
    }
/*
    private void callSubwayApi() {
        // TODO: API 호출 로직 (ex. SubwayApi를 사용하여 데이터를 가져오는 코드 등)
        SubwayApi subwayApi = ApiManager.createApi();
        Call<ApiResponseModel> call = subwayApi.getSubwayRouteInfo(
                "$2a$10$Sb/okQVuOfQ1CC9JQWxmeu6OcnkeRL8zS1kemO96dnFiDf8e0Qumu",
                "json",
                "lnCd_value",
                "mreaWideCd_value"
        );

        call.enqueue(new Callback<ApiResponseModel>() {
            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {
                if (response.isSuccessful()) {
                    ApiResponseModel responseData = response.body();
                    // UI에 데이터를 적용하는 작업 수행
                    updateUIWithData(responseData);
                } else {
                    // API 호출이 실패한 경우 처리
                    handleApiError();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseModel> call, Throwable t) {
                // 네트워크 오류 또는 예외 처리
                t.printStackTrace();
                // 사용자에게 오류 메시지를 표시하거나 다른 적절한 조치를 취할 수 있습니다.
                showErrorToUser();
            }
        });
    }*/

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
/*
    private void updateUIWithData(ApiResponseModel data) {
        // 받아온 데이터를 사용하여 UI를 업데이트하는 예시
        // 예를 들어, TextView에 데이터를 설정하는 등의 작업을 수행할 수 있습니다.
    }

    private void handleApiError() {
        // API 호출이 실패했을 때의 처리
        showErrorToUser();
    }

    private void showErrorToUser() {
        // 사용자에게 오류 메시지를 표시(임시)
        runOnUiThread(() -> {
            Toast.makeText(HomeFragment.this, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
        });
    }*/
}