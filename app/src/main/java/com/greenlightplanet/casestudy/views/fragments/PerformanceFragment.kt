package com.greenlightplanet.casestudy.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenlightplanet.casestudy.R
import com.greenlightplanet.casestudy.adaptors.PerformanceCountryAdaptor
import com.greenlightplanet.casestudy.adaptors.PerformanceRegionAdaptor
import com.greenlightplanet.casestudy.adaptors.PerformanceZoneAdaptor
import com.greenlightplanet.casestudy.databinding.FragmentPerformanceBinding
import com.greenlightplanet.casestudy.viewmodel.PerformanceViewModel
import dagger.hilt.android.AndroidEntryPoint


object ViewType {
    const val ZONE_VIEW_TYPE = "ZONE"
    const val COUNTRY_VIEW_TYPE = "COUNTRY"
    const val AREA_VIEW_TYPE = "AREA"
    const val REGION_VIEW_TYPE = "REGION"
}

@AndroidEntryPoint
class PerformanceFragment : Fragment(R.layout.fragment_performance) {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var callback: OnBackPressedCallback
    private val TAG: String? = PerformanceFragment::class.qualifiedName
    private lateinit var performanceRegionAdaptor: PerformanceRegionAdaptor
    private lateinit var performanceZoneAdaptor: PerformanceZoneAdaptor
    private lateinit var performanceCountryAdaptor: PerformanceCountryAdaptor
    private lateinit var binding: FragmentPerformanceBinding
//    private val performanceViewModel: PerformanceViewModel by activityViewModels()
    private val performanceViewModel:   PerformanceViewModel by lazy {
     ViewModelProvider(this)[PerformanceViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPerformanceBinding.bind(view)
        setToolbar()
        adaptorClickCallbacks()
        backPressHandle()
        callback.isEnabled = true
        performanceViewModel.viewType.postValue(performanceViewModel.viewSet)

    }

    private fun backPressHandle() {

        callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                Log.i(TAG, "handleOnBackPressed: ")
                updateView()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
    }

    private fun updateView() {

        when (performanceViewModel.viewSet) {
            ViewType.COUNTRY_VIEW_TYPE -> {
                callback.isEnabled = false
                findNavController().navigateUp()
            }
            ViewType.ZONE_VIEW_TYPE -> {
                performanceViewModel.viewType.postValue(ViewType.COUNTRY_VIEW_TYPE)
            }
            ViewType.REGION_VIEW_TYPE -> {
                performanceViewModel.viewType.postValue(ViewType.ZONE_VIEW_TYPE)
            }
        }
    }

    private fun adaptorClickCallbacks() {

        performanceViewModel.salesZoneCallback.observe(viewLifecycleOwner) {
            if (it != null) {
                performanceViewModel.salesZoneCallback.postValue(null)
                performanceViewModel.countryText  = "${it.country} Performance"
                performanceViewModel.viewType.postValue(ViewType.ZONE_VIEW_TYPE)
            }
        }

        performanceViewModel.salesRegionCallback.observe(viewLifecycleOwner) {
            if (it != null) {
                performanceViewModel.zoneText = "${it.zone} Performance"
                performanceViewModel.salesRegionCallback.postValue(null)
                performanceViewModel.viewType.postValue(ViewType.REGION_VIEW_TYPE)
            }
        }

        performanceViewModel.salesAreaCallback.observe(viewLifecycleOwner) {
            if (it != null) {
                performanceViewModel.salesAreaCallback.postValue(null)
                        callback.isEnabled = false
                performanceViewModel.areaText = it.region
                findNavController().navigate(PerformanceFragmentDirections.actionPerformanceFragmentToAreaPerformanceFragment())
            }
        }

        navigateViewCallback()
    }

    private fun navigateViewCallback() {
        performanceViewModel.viewType.observe(viewLifecycleOwner) {
            if (it != null) {
                performanceViewModel.viewSet = it

                when (it) {

                    ViewType.COUNTRY_VIEW_TYPE -> {
                        getCountries()
                    }
                    ViewType.ZONE_VIEW_TYPE -> {
                        getZones()
                    }
                    ViewType.REGION_VIEW_TYPE -> {
                        getRegions()
                    }
                }
                performanceViewModel.viewType.postValue(null)

            }
        }
    }


    private fun setRegionAdaptor() {
        performanceRegionAdaptor = PerformanceRegionAdaptor(performanceViewModel)
        binding.countryPerformanceRecyclerView.adapter = performanceRegionAdaptor
    }

    private fun getRegions() {

        binding.zoneTextView.text = getString(R.string.region)
        binding.performanceTextview.text = performanceViewModel.zoneText
        setRegionAdaptor()
        performanceViewModel.getAllSalesRegion().observe(viewLifecycleOwner) {
            performanceRegionAdaptor.submitList(it)
        }
    }

    private fun setZoneAdaptor() {
        performanceZoneAdaptor = PerformanceZoneAdaptor(performanceViewModel)
        binding.countryPerformanceRecyclerView.adapter = performanceZoneAdaptor
    }

    private fun getZones() {
        binding.zoneTextView.text = getString(R.string.zone)
        binding.performanceTextview.text = performanceViewModel.countryText
        setZoneAdaptor()
        performanceViewModel.getAllSalesZone().observe(viewLifecycleOwner) {
            performanceZoneAdaptor.submitList(it)
        }
    }

    private fun setupAdaptor() {
        performanceCountryAdaptor = PerformanceCountryAdaptor(performanceViewModel)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.countryPerformanceRecyclerView.layoutManager = layoutManager
        binding.countryPerformanceRecyclerView.adapter = performanceCountryAdaptor

        val dividerItemDecoration = DividerItemDecoration(
            binding.countryPerformanceRecyclerView.context,
            layoutManager.orientation
        )
        binding.countryPerformanceRecyclerView.addItemDecoration(dividerItemDecoration)
    }


    private fun setToolbar() {

        binding.toolbar.toolbar.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.toolbar.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.toolbar.title = getString(R.string.metrics)
        binding.toolbar.toolbar.setTitleTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.yellow
            )
        )
        binding.toolbar.toolbar.setNavigationOnClickListener {
            updateView()
        }
    }

    private fun getCountries() {

        binding.zoneTextView.text = getString(R.string.country)
        binding.performanceTextview.text = getString(R.string.country_performance)
        setupAdaptor()

        performanceViewModel.getSalesCountry().observe(viewLifecycleOwner) {
            performanceCountryAdaptor.submitList(it)
        }
    }

}