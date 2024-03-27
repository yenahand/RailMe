package com.subway.railme.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.subway.railme.R
import com.subway.railme.databinding.FragmentHomeKBinding
import com.subway.railme.viewmodel.SubWayInfoViewModel
import com.subway.railme.viewmodel.SubWayInfoViewModelFactory

@Suppress("UNREACHABLE_CODE")
class HomeKFragment : Fragment() {
    private var _binding: FragmentHomeKBinding? =null
    private val bindging get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this,SubWayInfoViewModelFactory())[SubWayInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeKBinding.inflate(layoutInflater,container,false)
        return bindging.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindging.searchBT.setOnClickListener {
            val word = bindging.searchStation.text.toString()
            viewModel.setSubwayInfo(word)
        }
    }
}