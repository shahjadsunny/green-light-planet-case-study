package com.greenlightplanet.casestudy.views.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greenlightplanet.casestudy.R
import com.greenlightplanet.casestudy.databinding.FragmentStartPerformanceBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StartPerformanceFragment : Fragment(R.layout.fragment_start_performance) {

    private lateinit var binding: FragmentStartPerformanceBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartPerformanceBinding.bind(view)

        setToolbar()
        listeners()
    }

    private fun listeners() {
        binding.toolbar.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.countryPerformanceButton.setOnClickListener {
            findNavController().navigate(StartPerformanceFragmentDirections.actionStartPerformanceFragmentToPerformanceFragment())
        }

    }

    private fun setToolbar() {


        binding.toolbar.toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.black))
        binding.toolbar.toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_arrow_back_24)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.toolbar.title = getString(R.string.metrics)
        binding.toolbar.toolbar.setTitleTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.yellow
            )
        )

    }
}