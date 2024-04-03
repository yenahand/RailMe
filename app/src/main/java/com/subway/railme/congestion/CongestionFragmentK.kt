package com.subway.railme.congestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.subway.railme.R
import com.subway.railme.databinding.FragmentCongestionKBinding

/**
 * 혼잡도 프래그먼트 코틀린
 */

class CongestionFragmentK : Fragment() {
    private var _binding : FragmentCongestionKBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCongestionKBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}