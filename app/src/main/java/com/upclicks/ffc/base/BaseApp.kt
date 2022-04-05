package com.upclicks.ffc.base

import android.app.Application
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.data.BaseURLConfigHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApp:Application(){
    @Inject
    lateinit var baseURLConfigHelper: BaseURLConfigHelper
    override fun onCreate() {
        super.onCreate()
        baseURLConfigHelper!!.configBaseUrl { baseUrl ->
            Keys.BASE_URL = baseUrl
//            baseUrlFinished.postValue(true)
        }
    }
}