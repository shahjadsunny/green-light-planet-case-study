package com.greenlightplanet.casestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.greenlightplanet.casestudy.model.SalesArea
import com.greenlightplanet.casestudy.repository.PerformanceDbRepository
import com.greenlightplanet.casestudy.views.fragments.ViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
@HiltViewModel
class AreaPerformanceViewModel  @Inject constructor(
    private var performanceDbRepository: PerformanceDbRepository
): ViewModel(){

    var currentSearched = String()
    var isAsc: Boolean = true
    var searchText  = MutableLiveData<String>()
    var viewSet  = ViewType.COUNTRY_VIEW_TYPE
    var areaText = String()

    fun getAllSalesArea(isAsc:Boolean): LiveData<List<SalesArea>> {
        return performanceDbRepository.getAllSalesArea(isAsc).asLiveData()
    }
    fun getAllSearchedSalesArea(searchedText:String,isAsc:Boolean): LiveData<List<SalesArea>> {
        return performanceDbRepository.getAllSearchedSalesArea(searchedText,isAsc).asLiveData()
    }

}