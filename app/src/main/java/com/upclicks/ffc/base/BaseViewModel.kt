package com.upclicks.ffc.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
}