package com.upclicks.ffc.ui.checkout.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.products.model.DeliveryTime

open class DeliveryTimeResponse {
    @SerializedName("today")
    @Expose
    var today: List<DeliveryTime>? = null
    @SerializedName("tomorrow")
    @Expose
    var tomorrow: List<DeliveryTime>? = null

}