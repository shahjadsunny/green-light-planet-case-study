package com.greenlightplanet.casestudy.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greenlightplanet.casestudy.model.SalesArea
import com.greenlightplanet.casestudy.model.SalesCountry
import com.greenlightplanet.casestudy.model.SalesRegion
import com.greenlightplanet.casestudy.model.SalesZone
import kotlinx.coroutines.flow.Flow

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
@Dao
interface PerformanceDao {

    //  Inserting Area sales list from api
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalesArea(salesArea: List<SalesArea>)

    // Inserting Country sales list from api
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalesCountry(salesCountry: List<SalesCountry>)

    // Inserting Region sales list from api
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalesRegion(salesRegion: List<SalesRegion>)

    // Inserting Zone sales list from api
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalesZone(salesZone: List<SalesZone>)


    // Fetching Area Sales list from db area order wise ase/desc
    @Query("SELECT * FROM sales_area ORDER BY CASE WHEN :isAsc = 1 THEN area END ASC, CASE WHEN :isAsc = 0 THEN area END DESC")
    fun getAllSalesArea(isAsc:Boolean): Flow<List<SalesArea>>

    // Fetching all Sales list with searched key  from db
    @Query("SELECT * FROM sales_area WHERE area Like :searchedText ORDER BY CASE WHEN :isAsc = 1 THEN area END ASC, CASE WHEN :isAsc = 0 THEN area END DESC")
    fun getAllSearchedSalesArea(searchedText: String,isAsc:Boolean): Flow<List<SalesArea>>

    // Fetching all Country sales list  from db
    @Query("SELECT * FROM sales_country")
    fun getSalesCountry(): Flow<List<SalesCountry>>

    // Fetching all Region sales list  from db
    @Query("SELECT * FROM sales_region")
    fun getAllSalesRegion(): Flow<List<SalesRegion>>

    // Fetching all Zone sales list  from db
    @Query("SELECT * FROM sales_zone")
    fun getAllSalesZone(): Flow<List<SalesZone>>


    // Delete all Area sales list  from db
    @Query("DELETE  FROM sales_area")
    suspend fun deleteAllSalesArea()


    // Delete all Country sales list  from db
    @Query("DELETE  FROM sales_country")
    suspend fun  deleteSalesCountry()

    // Delete all Region  sales list  from db
    @Query("DELETE FROM sales_region")
    suspend fun  deleteAllSalesRegion()

    // Delete all Zone sales list  from db
    @Query("DELETE FROM sales_zone")
    suspend fun  deleteAllSalesZone()


}