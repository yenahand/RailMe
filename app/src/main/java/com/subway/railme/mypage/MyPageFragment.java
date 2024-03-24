package com.subway.railme.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.subway.railme.databinding.FragmentMyPageBinding;


public class MyPageFragment extends Fragment {

    private FragmentMyPageBinding binding_;
    private String strNick, strEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding_ = FragmentMyPageBinding.inflate(inflater, container, false);
        return binding_.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fragment를 호스팅하는 Activity에서 Intent를 가져옵니다.
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            // Intent에서 데이터를 가져옵니다.
            strNick = intent.getStringExtra("name");
            strEmail = intent.getStringExtra("Email");

            // 가져온 데이터를 TextView에 설정합니다.
            binding_.tvLoginArea.setText(strNick);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding_ = null;
    }
}