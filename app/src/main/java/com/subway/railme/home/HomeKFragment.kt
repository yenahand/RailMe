package com.subway.railme.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.subway.railme.congestion.CongestionModel
import com.subway.railme.databinding.FragmentHomeKBinding
import com.subway.railme.db.MyApplication
import com.subway.railme.home.dialog.ArrivalInfoDialog
import com.subway.railme.viewmodel.SubWayInfoViewModel
import com.subway.railme.viewmodel.SubWayInfoViewModelFactory

class HomeKFragment : Fragment() {
    private var _binding: FragmentHomeKBinding? = null
    private val binding get() = _binding!!

    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f
    private lateinit var mImageView: ImageView
    private var offsetX = 0f
    private var offsetY = 0f
    private var lastX = 0f
    private var lastY = 0f
    private val viewModel by lazy {
        ViewModelProvider(this, SubWayInfoViewModelFactory())[SubWayInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeKBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImageView = binding.ivSubwayMap

        // 확대/축소 제스처 감지기 초기화
        context?.let {
            mScaleGestureDetector = ScaleGestureDetector(it, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = 1f.coerceAtLeast(scaleFactor.coerceAtMost(30.0f))

                    val focusX = detector.focusX
                    val focusY = detector.focusY

                    mImageView.pivotX = focusX
                    mImageView.pivotY = focusY
                    mImageView.scaleX = scaleFactor
                    mImageView.scaleY = scaleFactor

                    return true
                }
            })
        }

        // 이미지뷰 터치 리스너 설정
        mImageView.setOnTouchListener { v, event ->
            when (event.pointerCount) {
                1 -> handleSingleTouch(event)
                2 -> mScaleGestureDetector?.onTouchEvent(event)
            }
            true
        }

        binding.searchBT.setOnClickListener {
            val word = binding.searchStation.text.toString()
            val currentDate = CongestionModel.getCurrentDate()
            val currentTime = CongestionModel.getCurrentTime()
            MyApplication.prefs.setTime("currentTime", currentTime)
            MyApplication.prefs.setDate("currentDate", currentDate)
            viewModel.setSubwayInfo(word)
            viewModel.searchWay.observe(viewLifecycleOwner) { arrivalModels ->
                arrivalModels?.let {
                    val dialog = ArrivalInfoDialog(requireContext(), it)
                    dialog.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 한 손가락으로 화면을 이동하는 경우 처리
    private fun handleSingleTouch(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.x - lastX
                val dy = event.y - lastY

                mImageView.translationX += dx
                mImageView.translationY += dy

                lastX = event.x
                lastY = event.y
            }
        }
    }
}