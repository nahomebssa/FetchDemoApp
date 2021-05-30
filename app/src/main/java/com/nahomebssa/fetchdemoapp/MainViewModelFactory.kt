package com.nahomebssa.fetchdemoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nahomebssa.fetchdemoapp.repository.Repository

/**
 * Factory class to make a ViewModel, which is given to ViewModelProvider
 */
class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}