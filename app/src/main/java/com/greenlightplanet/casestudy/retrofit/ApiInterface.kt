package com.greenlightplanet.casestudy.retrofit

import com.greenlightplanet.casestudy.model.PerformanceModel
import com.greenlightplanet.casestudy.retrofit.URLS.GET_SAMPLE_PERFORMANCE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

//
// @Author: Shahjad Ansari
// @Date: 03/06/22
interface ApiInterface {


    //TODO - > get sample performance from server
    @GET(GET_SAMPLE_PERFORMANCE)
    suspend fun getPerformanceData(): Response<PerformanceModel>



    //TODO - > create Ok http
    companion object {
        fun create():ApiInterface{

                val okHttpClient = OkHttpClient()
                val interceptor = HttpLoggingInterceptor()
                val timeout: Long = 120
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                return Retrofit.Builder()
                    .baseUrl(URLS.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        okHttpClient.newBuilder()
                            .connectTimeout(timeout, TimeUnit.SECONDS)
                            .readTimeout(timeout, TimeUnit.SECONDS)
                            .writeTimeout(timeout, TimeUnit.SECONDS)
                            .addInterceptor(interceptor)
                            .build()
                    )
                    .build().create(ApiInterface::class.java)
        }
    }

}