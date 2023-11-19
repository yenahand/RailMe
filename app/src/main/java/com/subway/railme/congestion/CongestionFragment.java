package com.subway.railme.congestion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subway.railme.R;
import com.subway.railme.databinding.FragmentCongestionBinding;
import com.subway.railme.databinding.FragmentHomeBinding;


public class CongestionFragment extends Fragment {
    private FragmentCongestionBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCongestionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}///하이