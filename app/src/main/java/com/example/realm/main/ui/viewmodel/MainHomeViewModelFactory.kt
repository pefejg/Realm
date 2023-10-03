package com.example.realm.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.realm.main.background.datasource.MainHomeDataSource
import com.example.realm.main.background.repository.MainHomeRepository

@Suppress("UNCHECKED_CAST")
class MainHomeViewModelFactory: ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainHomeViewModel::class.java)){
            return MainHomeViewModel(MainHomeRepository(MainHomeDataSource())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}