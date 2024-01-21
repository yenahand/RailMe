//혼잡도페이지에서 지하철아이콘을 혼잡률에따라 색상이 변경되도록 하려하는데 일단 파이썬부분은 아직 비워뒀습니다..

package com.subway.railme.congestion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

//import com.chaquo.python.PyObject;
//import com.chaquo.python.Python;
import com.subway.railme.R;
import com.subway.railme.databinding.FragmentCongestionBinding;

public class CongestionFragment extends Fragment {

    private FragmentCongestionBinding binding;
    private ImageView imageView;
    private int congestionLevel; // 혼잡도 값을 저장하는 변수(임시)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCongestionBinding.inflate(inflater, container, false);
        imageView = binding.ivCongestion;

        // AsyncTask를 사용하여 백그라운드에서 혼잡도 값을 가져오기
       // new GetCongestionLevelTask().execute();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

   /* private class GetCongestionLevelTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            // Python 코드 실행
            Python python = Python.getInstance();
            PyObject calculateCongestion = python.getModule("calculate_congestion");
            PyObject result = calculateCongestion.callAttr("calculate_congestion");

            return result.toInt();
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            congestionLevel = result;

            // 혼잡도에 따라 이미지뷰의 색상을 설정
            setImageViewColor();
        }
    }

    private void setImageViewColor() {
        if (congestionLevel > 70) {
            imageView.setColorFilter(getResources().getColor(R.color.red)); // 빨간색
        } else if (congestionLevel >= 50 && congestionLevel <= 70) {
            imageView.setColorFilter(getResources().getColor(R.color.orange)); // 주황색
        } else if (congestionLevel >= 30 && congestionLevel < 50) {
            imageView.setColorFilter(getResources().getColor(R.color.yellow)); // 노란색
        } else {
            imageView.setColorFilter(getResources().getColor(R.color.green)); // 초록색
        }
    }*/
}
