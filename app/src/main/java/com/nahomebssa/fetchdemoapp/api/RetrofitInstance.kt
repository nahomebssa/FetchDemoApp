package com.nahomebssa.fetchdemoapp.api

import com.nahomebssa.fetchdemoapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton class to store ref to application retrofit instance
 */
object RetrofitInstance {

    /**
     * Used to create an instance of retrofit
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Creates an instance of Retrofit with our api
     */
    val api: FetchApi by lazy {
        retrofit.create(FetchApi::class.java)
    }

}