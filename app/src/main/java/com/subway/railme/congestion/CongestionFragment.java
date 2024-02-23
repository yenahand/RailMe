//혼잡도파이썬 파일 받은 후에 나머지 부분 작업 가능할 것 같습니다 ..
//일단 혼잡도는 홈프래그먼트에서 역을 검색하면 그 역으로 설정해서 혼잡도를 표시하도록 구현중입니다
package com.subway.railme.congestion;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

//import com.chaquo.python.PyObject;
//import com.chaquo.python.Python;
import com.subway.railme.R;
import com.subway.railme.databinding.FragmentCongestionBinding;

public class CongestionFragment extends Fragment {

    private FragmentCongestionBinding binding;
    private ImageView imageView;
    private int congestionLevel; // 혼잡도 값을 저장하는 변수(임시)
    private TextView stationNameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCongestionBinding.inflate(inflater, container, false);
        imageView = binding.ivCongestion;
        stationNameTextView = binding.congestionText;

        // HomeFragment로부터 전달받은 역 이름을 표시
        Bundle arguments = getArguments();
        if (arguments != null) {
            String stationName = arguments.getString("stationName");
            if (stationName != null) {
                stationNameTextView.setText(stationName);
            }
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

   /*private class GetCongestionLevelTask extends AsyncTask<Void, Void, Integer> {
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

    //정보설명창(팝업창)
    private void showCongestionInfoPopup(View view) {
        LayoutInflater inflater = (LayoutInflater) requireContext().getSystemService(requireContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // 팝업 창 위치
        popupWindow.showAtLocation(view, 0, 50, 200); //변경예정

        // 팝업 창 닫기 버튼
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button closeButton = popupView.findViewById(R.id.popup_back);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
