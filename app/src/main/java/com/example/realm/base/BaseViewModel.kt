package com.example.realm.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(private val mBaseRepository: BaseRepository): ViewModel() {
    protected var _progressMessage = MutableLiveData<String?>()
    val mProgressMessage: LiveData<String?> = _progressMessage

    protected var _toastMessage = MutableLiveData<String?>()
    val mToastMessage: LiveData<String?> = _toastMessage
}