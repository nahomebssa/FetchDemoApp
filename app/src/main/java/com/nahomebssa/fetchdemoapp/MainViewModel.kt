package com.nahomebssa.fetchdemoapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nahomebssa.fetchdemoapp.model.Item
import com.nahomebssa.fetchdemoapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<List<Item>>> = MutableLiveData()

    fun getItems() {
        viewModelScope.launch {
            myResponse.value = repository.getItems()
        }
    }
}