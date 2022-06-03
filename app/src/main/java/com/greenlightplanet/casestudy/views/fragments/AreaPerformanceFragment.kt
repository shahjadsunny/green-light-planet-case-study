package com.greenlightplanet.casestudy.views.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenlightplanet.casestudy.R
import com.greenlightplanet.casestudy.adaptors.PerformanceAreaAdaptor
import com.greenlightplanet.casestudy.databinding.FragmentAreaPerformanceBinding
import com.greenlightplanet.casestudy.viewmodel.AreaPerformanceViewModel
import com.greenlightplanet.casestudy.viewmodel.PerformanceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AreaPerformanceFragment : Fragment(R.layout.fragment_area_performance) {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var performanceAreaSearchAdaptor: PerformanceAreaAdaptor
    private lateinit var performanceAreaAdaptor: PerformanceAreaAdaptor
    private lateinit var binding: FragmentAreaPerformanceBinding
//    private val performanceViewModel: PerformanceViewModel by activityViewModels()
//    private val  areaPerformanceViewModel: AreaPerformanceViewModel by activityViewModels()
    private val areaPerformanceViewModel:   AreaPerformanceViewModel by lazy {
        ViewModelProvider(this)[AreaPerformanceViewModel::class.java]
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAreaPerformanceBinding.bind(view)
        init()
        setToolbar()
        getSalesArea()
        searchListener()
        listenersAscDsc()
        asDescUpdate()
    }

    private fun init() {
        areaPerformanceViewModel.viewSet = ViewType.REGION_VIEW_TYPE
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(
            binding.areaPerformanceRecyclerView.context,
            layoutManager.orientation
        )
        binding.areaPerformanceRecyclerView.addItemDecoration(dividerItemDecoration)

        val setText = "${areaPerformanceViewModel.areaText} Performance"
        binding.performanceTextview.text = setText

        val id = binding.searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val editText =  binding.searchView.findViewById<EditText>(id)
        editText.setTextColor(Color.BLACK)
    }

    private fun listenersAscDsc() {

        binding.linearLayoutViewAscDesc.setOnClickListener {
            areaPerformanceViewModel.isAsc = !areaPerformanceViewModel.isAsc

            asDescUpdate()
            if (areaPerformanceViewModel.currentSearched.isNotEmpty()){
                areaPerformanceViewModel.searchText.postValue(areaPerformanceViewModel.currentSearched)
            }else{
                getSalesArea()
            }
        }
    }

    private fun asDescUpdate() {

        if (areaPerformanceViewModel.isAsc){
            binding.ascDescImgView.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
        }else{
            binding.ascDescImgView.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
        }
    }

    private fun searchListener() {

        searchCallback()

        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0.isNullOrEmpty()){
                    areaPerformanceViewModel.currentSearched = ""
                    getSalesArea()
                }else{
                    areaPerformanceViewModel.searchText.postValue(p0)
                }
                return false
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        areaPerformanceViewModel.currentSearched = ""
    }
    private fun searchCallback() {

        areaPerformanceViewModel.searchText.observe(viewLifecycleOwner) {
            if (it != null) {
                performanceAreaSearchAdaptor = PerformanceAreaAdaptor()
                binding.areaPerformanceRecyclerView.adapter = performanceAreaSearchAdaptor
                lifecycleScope.launch {
                    areaPerformanceViewModel.currentSearched = "%$it%"
                    areaPerformanceViewModel.getAllSearchedSalesArea(areaPerformanceViewModel.currentSearched,areaPerformanceViewModel.isAsc).observe(viewLifecycleOwner) { list->
                        performanceAreaSearchAdaptor.submitList(list)
                    }
                }
                areaPerformanceViewModel.searchText.postValue(null)
            }
        }
    }

    private fun adaptorSetup() {
        performanceAreaAdaptor = PerformanceAreaAdaptor()
        binding.areaPerformanceRecyclerView.adapter = performanceAreaAdaptor
    }

    private fun getSalesArea() {
        adaptorSetup()
        lifecycleScope.launch {
            areaPerformanceViewModel.getAllSalesArea(areaPerformanceViewModel.isAsc).observe(viewLifecycleOwner){
                performanceAreaAdaptor.submitList(it)
            }
        }
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
            findNavController().navigateUp()
        }
    }
}