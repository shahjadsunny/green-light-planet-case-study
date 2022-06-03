package com.greenlightplanet.casestudy.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.greenlightplanet.casestudy.model.SalesArea
import com.greenlightplanet.casestudy.model.SalesCountry
import com.greenlightplanet.casestudy.model.SalesRegion
import com.greenlightplanet.casestudy.model.SalesZone
import com.greenlightplanet.casestudy.storage.dao.PerformanceDao
import com.greenlightplanet.casestudy.utility.MyConstant.DATABASE_NAME

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
@Database(
    entities = [
        SalesArea::class,
        SalesCountry::class,
        SalesRegion::class,
        SalesZone::class,
    ],
    version = 2,
//    exportSchema = false
)
 abstract class AppDatabase: RoomDatabase()  {

    //  Singleton instantiation var
    abstract fun performanceDao(): PerformanceDao

    companion object {

        //  Singleton instantiation var
        @Volatile private var instance: AppDatabase? = null


        // getInstance app Database
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // create Database Instance
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}