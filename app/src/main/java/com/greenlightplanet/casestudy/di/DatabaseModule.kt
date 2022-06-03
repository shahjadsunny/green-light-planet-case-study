package com.greenlightplanet.casestudy.di

import android.content.Context
import com.greenlightplanet.casestudy.storage.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    // initialize single Database Instance for entire app
    @Singleton
    @Provides
     fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}