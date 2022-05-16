package com.upclicks.ffc.base

import android.app.Application
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.data.BaseURLConfigHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApp:Application(){
    override fun onCreate() {
        super.onCreate()
    }
}