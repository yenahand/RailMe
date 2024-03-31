package com.subway.railme.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.subway.railme.databinding.FragmentHomeKBinding
import com.subway.railme.home.dialog.ArrivalInfoDialog
import com.subway.railme.viewmodel.SubWayInfoViewModel
import com.subway.railme.viewmodel.SubWayInfoViewModelFactory

class HomeKFragment : Fragment() {
    private var _binding: FragmentHomeKBinding? =null
    private val binding get() = _binding!!

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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