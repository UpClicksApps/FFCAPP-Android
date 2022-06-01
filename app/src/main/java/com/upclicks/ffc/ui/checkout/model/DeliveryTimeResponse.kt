package com.upclicks.ffc.ui.checkout.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
open class DeliveryTimeResponse {
    @SerializedName("today")
    @Expose
    var today: List<String>? = null
    @SerializedName("tomorrow")
    @Expose
    var tomorrow: List<String>? = null
}