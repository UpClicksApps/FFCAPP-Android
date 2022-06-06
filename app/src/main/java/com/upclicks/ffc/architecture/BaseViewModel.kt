package com.upclicks.ffc.architecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
}