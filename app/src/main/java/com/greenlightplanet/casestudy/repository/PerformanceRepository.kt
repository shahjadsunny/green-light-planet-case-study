package com.greenlightplanet.casestudy.repository

import com.greenlightplanet.casestudy.retrofit.ApiInterface
import javax.inject.Inject

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
class PerformanceRepository @Inject constructor(var apiInterface: ApiInterface) {

    suspend fun getPerformanceData() =  apiInterface.getPerformanceData()

}