package com.greenlightplanet.casestudy.di

import com.greenlightplanet.casestudy.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {



    //TODO - > initialize single Okhttp Instance for entire app
    @Singleton
    @Provides
    fun getRetrofitInstance(): ApiInterface {
        return ApiInterface.create()
    }

}