package com.greenlightplanet.casestudy.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.greenlightplanet.casestudy.model.*
import com.greenlightplanet.casestudy.repository.PerformanceDbRepository
import com.greenlightplanet.casestudy.repository.PerformanceRepository
import com.greenlightplanet.casestudy.views.fragments.ViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// @Author: Shahjad Ansari
// @Date: 03/06/22
@HiltViewModel
class PerformanceViewModel @Inject constructor(
    private var performanceDbRepository: PerformanceDbRepository,
) : ViewModel(){

    val salesZoneCallback = MutableLiveData<SalesCountry>()
    val salesRegionCallback = MutableLiveData<SalesZone>()
    val salesAreaCallback = MutableLiveData<SalesRegion>()
    val viewType = MutableLiveData<String>()

     var zoneText = String()
     var areaText = String()
     var countryText = String()

    var viewSet  = ViewType.COUNTRY_VIEW_TYPE


    fun getSalesCountry():LiveData<List<SalesCountry>>{
        return performanceDbRepository.getSalesCountry().asLiveData()
    }
    fun getAllSalesRegion():LiveData<List<SalesRegion>>{
        return performanceDbRepository.getAllSalesRegion().asLiveData()
    }
    fun getAllSalesZone():LiveData<List<SalesZone>>{
        return performanceDbRepository.getAllSalesZone().asLiveData()
    }


}