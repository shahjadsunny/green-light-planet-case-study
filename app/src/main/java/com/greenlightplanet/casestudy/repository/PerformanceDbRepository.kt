package com.greenlightplanet.casestudy.repository

import com.greenlightplanet.casestudy.model.SalesArea
import com.greenlightplanet.casestudy.model.SalesCountry
import com.greenlightplanet.casestudy.model.SalesRegion
import com.greenlightplanet.casestudy.model.SalesZone
import com.greenlightplanet.casestudy.storage.AppDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
class PerformanceDbRepository @Inject constructor(
    private val database: AppDatabase
) {

    suspend fun insertSalesArea(salesArea: List<SalesArea>) {
        database.performanceDao().insertSalesArea(salesArea)

    }

    suspend fun insertSalesCountry(salesCountry: List<SalesCountry>) {
        database.performanceDao().insertSalesCountry(salesCountry)
    }

    suspend fun insertSalesRegion(salesRegion: List<SalesRegion>) {

        database.performanceDao().insertSalesRegion(salesRegion)
    }

    suspend fun insertSalesZone(salesZone: List<SalesZone>) {

        database.performanceDao().insertSalesZone(salesZone)
    }

    fun getAllSalesArea(isAsc:Boolean): Flow<List<SalesArea>> {
        return database.performanceDao().getAllSalesArea(isAsc)
    }

    fun getSalesCountry(): Flow<List<SalesCountry>> {
        return database.performanceDao().getSalesCountry()
    }

    fun getAllSalesRegion(): Flow<List<SalesRegion>> {
        return database.performanceDao().getAllSalesRegion()
    }

    fun getAllSalesZone(): Flow<List<SalesZone>> {
        return database.performanceDao().getAllSalesZone()
    }


    fun getAllSearchedSalesArea(searchedText: String,isAsc:Boolean): Flow<List<SalesArea>> {
        return database.performanceDao().getAllSearchedSalesArea(searchedText,isAsc)
    }


   suspend fun deleteAllFirstData() {
        database.performanceDao().deleteAllSalesArea()
        database.performanceDao().deleteSalesCountry()
        database.performanceDao().deleteAllSalesRegion()
        database.performanceDao().deleteAllSalesZone()
    }




}