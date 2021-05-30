package com.nahomebssa.fetchdemoapp.api

import com.nahomebssa.fetchdemoapp.model.Item
import retrofit2.Response
import retrofit2.http.GET

interface FetchApi {

    @GET("hiring.json")
    suspend fun getItems(): Response<List<Item>>

}