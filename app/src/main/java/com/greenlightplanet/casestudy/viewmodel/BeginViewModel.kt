package com.greenlightplanet.casestudy.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenlightplanet.casestudy.model.PerformanceModel
import com.greenlightplanet.casestudy.repository.PerformanceDbRepository
import com.greenlightplanet.casestudy.repository.PerformanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
@HiltViewModel
class BeginViewModel @Inject constructor(
    private var performanceRepository: PerformanceRepository,
    private var performanceDbRepository: PerformanceDbRepository
    ):ViewModel() {


    fun fetchPerformanceData() {
        viewModelScope.launch {
            getPerformanceApiCall()
        }
    }

    private suspend fun getPerformanceApiCall() = withContext(Dispatchers.IO) {
        try {
            val response = performanceRepository.getPerformanceData()

            if(response.isSuccessful) {
                val responseData = response.body()
                updateDatabase(responseData)
            } else {
                val message = response?.errorBody()?.string()
//                com.greenLightPlanet.casestudy.utility.Result.Error(IOException(message.getString("message")))
            }

        } catch (e: Exception) {
//            com.greenLightPlanet.casestudy.utility.Result.Error(IOException(e.message))
            Log.i("TAG", "getPerformanceData: ${e.message}")
        }

    }

    private suspend fun updateDatabase(responseData: PerformanceModel?) {
        performanceDbRepository.deleteAllFirstData()
        responseData?.ResponseData?.sales_area?.let {
            performanceDbRepository.insertSalesArea(it)
        }

        responseData?.ResponseData?.sales_country?.let {
            performanceDbRepository.insertSalesCountry(it)
        }

        responseData?.ResponseData?.sales_region?.let {
            performanceDbRepository.insertSalesRegion(it)
        }

        responseData?.ResponseData?.sales_zone?.let {
            performanceDbRepository.insertSalesZone(it)
        }

    }
}