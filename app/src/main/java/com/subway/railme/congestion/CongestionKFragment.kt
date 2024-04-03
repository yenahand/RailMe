package com.subway.railme.congestion

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.subway.railme.R
import com.subway.railme.databinding.FragmentCongestionKBinding

/**
 * 혼잡도 프래그먼트 코틀린
 */

class CongestionKFragment : Fragment() {
    private var _binding : FragmentCongestionKBinding? = null
    private val binding get() = _binding!!
    private lateinit var stationNameTextView:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCongestionKBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stationNameTextView = binding.tvCurrentStation

        val arguments = arguments
        if (arguments != null) {
            val stationName = arguments.getString("stationName")
            if (stationName != null) {
                stationNameTextView.text = stationName
            }
        }

        binding.ibCongestionInfo.setOnClickListener {
            showCongestionInfoPopup(binding.root)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 정보설명창(팝업창)
    //TODO: 팝업창 크기 및 위치 조정하기
    private fun showCongestionInfoPopup(view: View) {
        val inflater = LayoutInflater.from(requireContext())
        val popupView = inflater.inflate(R.layout.popup, null)
        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true
        val popupWindow = PopupWindow(popupView, width, height, focusable)

        // 팝업 창 위치
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0) // 변경 예정

        // 팝업 창 닫기 버튼
        val closeButton = popupView.findViewById<Button>(R.id.popup_back)
        closeButton.setOnClickListener { v: View? -> popupWindow.dismiss() }
    }
}

