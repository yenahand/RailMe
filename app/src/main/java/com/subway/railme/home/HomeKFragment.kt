package com.subway.railme.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.subway.railme.databinding.FragmentHomeKBinding
import com.subway.railme.home.dialog.ArrivalInfoDialog
import com.subway.railme.viewmodel.SubWayInfoViewModel
import com.subway.railme.viewmodel.SubWayInfoViewModelFactory

class HomeKFragment : Fragment() {
    private var _binding: FragmentHomeKBinding? =null
    private val binding get() = _binding!!

    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f
    private lateinit var mImageView: ImageView
    private val viewModel by lazy {
        ViewModelProvider(this,SubWayInfoViewModelFactory())[SubWayInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeKBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImageView = binding.ivSubwayMap

        mScaleGestureDetector = context?.let {
            ScaleGestureDetector(it, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
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
        mImageView.setOnTouchListener { _, event ->
            mScaleGestureDetector?.onTouchEvent(event)
            true
        }

        binding.searchBT.setOnClickListener {
            val word = binding.searchStation.text.toString()
            viewModel.setSubwayInfo(word)
        }
        viewModel.searchWay.observe(viewLifecycleOwner) { arrivalModels ->
            arrivalModels?.let {
                val dialog = ArrivalInfoDialog(requireContext(), it)
                dialog.show()
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}