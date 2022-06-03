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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Begin", "onCreate: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("Begin", "onDestroyView: ")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Begin", "onViewCreated: ")
        binding = FragmentBeginBinding.bind(view)

        listeners()

        getData()
    }

    private fun getData() {

//        viewLifecycleOwner.lifecycleScope.launch {
//
//        }
//        lifecycleScope.launch {
        beginViewModel.fetchPerformanceData()
//        }


//        viewLifecycleOwner.lifecycleScope.launch {
//
//            var i=0
//            while (true){
//                delay(1000)
//                if (i==10)
//                    break
//                Log.i("Begin","viewLifecycleOwner.lifecycleScope"+i++)
//            }
//        }
//        lifecycleScope.launch {
//            var i=0
//            while (true){
//                delay(1000)
//                if (i==10)
//                    break
//                Log.i("Begin", "lifecycleScope"+i++)
//            }
//        }
//        lifecycleScope.launchWhenCreated {
//            var i=0
//            while (true){
//                delay(1000)
//                if (i==10)
//                    break
//                Log.i("Begin", "lifecycleScope launchWhenStarted"+i++)
//            }
//        }
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

    override fun onResume() {
        super.onResume()
        Log.i("Begin", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Begin", "onPause: ")

    }

    override fun onStop() {
        super.onStop()
        Log.i("Begin", "onStop: ")

    }
}