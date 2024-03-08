/*노선도 부분 api연동해서 하나하나 구현하려면 겨울방학 내로는 못 끝낼 것 같다고 하시는데 이부분
   괜찮은 해결방안 있을까요? */
package com.subway.railme.home;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import com.subway.railme.R;
import com.subway.railme.congestion.CongestionFragment;
import com.subway.railme.home.API.ApiManager;
import com.subway.railme.home.API.ApiResponseModel;
import com.subway.railme.home.API.RealtimeArrival;
import com.subway.railme.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ScaleGestureDetector scaleGestureDetector;  // 확대/축소
    private GestureDetector gestureDetector;  // 스크롤
    private ArrayAdapter<String> stationAdapter;  // 역 리스트
    private List<RealtimeArrival> stationList;  // 실시간 도착 정보

    //뷰 업데이트
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);  // 뷰 바인딩 초기화

        ProgressBar progressBar = binding.progressBar;
        View view = binding.getRoot();

        ImageView imageViewZoomable = binding.ivSubwayMap; //노선도
        AppCompatEditText searchStation = binding.searchStation;//역 검색창
        ListView stationListView = binding.stationListView;

        stationList = new ArrayList<>();
        stationAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1);
        stationListView.setAdapter(stationAdapter);

        scaleGestureDetector = new ScaleGestureDetector(requireContext(), new ScaleListener());
        imageViewZoomable.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            gestureDetector.onTouchEvent(event);
            return true;
        });

        gestureDetector = new GestureDetector(requireContext(), new ScrollListener());

        searchStation.setOnEditorActionListener((v, actionId, event) -> {

            binding.progressBar.setVisibility(View.VISIBLE);

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String stationName = searchStation.getText().toString();
                if (!stationName.isEmpty()) {
                    callSubwayApi(stationName);
                } else {
                    Toast.makeText(requireContext(), "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });

        return view;
    }

    // API 호출 메서드
    private void callSubwayApi(String stationName) {
        Call<ApiResponseModel> call = ApiManager.getRealtimeArrivalInfo(stationName);
        call.enqueue(new Callback<ApiResponseModel>() {
            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {

                binding.progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    ApiResponseModel apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getRealtimeArrivalList() != null) {
                        stationList = apiResponse.getRealtimeArrivalList();

                        if (stationList.isEmpty()) {
                            Toast.makeText(requireContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            updateStationListView();

                            // 검색한 역의 이름을 CongestionFragment로 직접 전달
                            CongestionFragment congestionFragment = new CongestionFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("stationName", stationName);
                            congestionFragment.setArguments(bundle);

                            getFragmentManager().beginTransaction()
                                    //.replace(R.id.tv_congestionText, congestionFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }

                }
                 else {
                    Log.d("HomeFragment", "서버 응답 실패");
                    Toast.makeText(requireContext(), "서버 응답에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ApiResponseModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);

                Log.d("HomeFragment", "통신 실패", t);
                Toast.makeText(requireContext(), "통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // UI 업데이트 메서드
    private void updateStationListView() {
        stationAdapter.clear();
        for (RealtimeArrival station : stationList) {
            stationAdapter.add(station.getStatnNm());
        }
        stationAdapter.notifyDataSetChanged();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            float currentScale = binding.ivSubwayMap.getScaleX();
            float newScale = currentScale * scaleFactor;
            newScale = Math.max(0.1f, Math.min(newScale, 5.0f));
            binding.ivSubwayMap.setScaleX(newScale);
            binding.ivSubwayMap.setScaleY(newScale);
            return true;
        }
    }

    private class ScrollListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float currentTranslationX = binding.ivSubwayMap.getTranslationX();
            float currentTranslationY = binding.ivSubwayMap.getTranslationY();
            binding.ivSubwayMap.setTranslationX(currentTranslationX - distanceX);
            binding.ivSubwayMap.setTranslationY(currentTranslationY - distanceY);
            return true;
        }
    }
}
