package com.subway.railme.mypage;

import android.content.Context;
import android.hardware.lights.LightsManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.RuntimeExecutionException;
import com.subway.railme.R;
import com.subway.railme.databinding.FragmentMpTimeBinding;

import java.util.List;
import java.util.Vector;

import javax.annotation.Nullable;

public class MpTimeFragment extends Fragment {

    private FragmentMpTimeBinding binding;
    private FragmentMpTimeListener fragmentMpTimeListener;

    public interface FragmentMpTimeListener{
        void onInputASent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMpTimeBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void updateEditText(CharSequence newText) {
        binding.tvRootTimeLog.setText(newText);
    }

    @Override
    public void onAttach(@Nullable Context context) {
        super.onAttach(context);
        if(context instanceof FragmentMpTimeListener){
            fragmentMpTimeListener = (FragmentMpTimeListener)context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentMpTimeListener = null;
    }
}