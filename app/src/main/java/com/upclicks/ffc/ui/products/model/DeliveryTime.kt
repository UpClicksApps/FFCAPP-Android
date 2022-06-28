package com.upclicks.ffc.ui.products.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class DeliveryTime {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("time")
    @Expose
    var time: String? = null
}