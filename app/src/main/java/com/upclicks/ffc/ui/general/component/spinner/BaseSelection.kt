package com.upclicks.ffc.ui.general.component.spinner

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseSelection {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("deliveryFees")
    @Expose
    var deliveryFees: Double? = null
}