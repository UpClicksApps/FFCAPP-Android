package com.upclicks.ffc.ui.cart.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class CartActionResponse {
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("currentCartItemsCount")
    @Expose
    var currentCartItemsCount: Int? = null
}