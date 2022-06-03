package com.greenlightplanet.casestudy.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.greenlightplanet.casestudy.R
import com.greenlightplanet.casestudy.databinding.FragmentBeginBinding
import com.greenlightplanet.casestudy.viewmodel.BeginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BeginFragment : Fragment(R.layout.fragment_begin) {

    private lateinit var binding: FragmentBeginBinding
//    private val performanceViewModel: PerformanceViewModel by activityViewModels()
    private val beginViewModel: BeginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Begin", "onViewCreated: ")
        binding = FragmentBeginBinding.bind(view)

        listeners()

        getData()
    }

    private fun getData() {
        beginViewModel.fetchPerformanceData()
    }

    private fun listeners() {

        binding.beginPlayImageView.setOnClickListener {
            findNavController().navigate(BeginFragmentDirections.actionBeginFragmentToPerformanceFragment())
        }
    }

    override fun onDestroy() {
        Log.i("Begin", "onDestroy: ")

        super.onDestroy()
    }


}