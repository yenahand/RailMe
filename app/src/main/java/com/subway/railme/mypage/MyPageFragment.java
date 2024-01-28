package com.subway.railme.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;

import com.subway.railme.databinding.ActivityLoginBinding;
import com.subway.railme.databinding.FragmentMyPageBinding;
import com.subway.railme.login.LoginViewModel;


public class MyPageFragment extends Fragment {

    private FragmentMyPageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}