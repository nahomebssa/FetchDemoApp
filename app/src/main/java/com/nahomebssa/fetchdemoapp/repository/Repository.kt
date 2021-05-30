package com.nahomebssa.fetchdemoapp.repository

import com.nahomebssa.fetchdemoapp.api.RetrofitInstance
import com.nahomebssa.fetchdemoapp.model.Item
import retrofit2.Response

/**
 * Stores the methods to access data stored elsewhere (the JSON file)
 */
class Repository {
    suspend fun getItems(): Response<List<Item>> {
        return RetrofitInstance.api.getItems()
    }
}