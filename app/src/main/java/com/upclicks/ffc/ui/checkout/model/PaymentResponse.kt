package com.upclicks.ffc.ui.checkout.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class PaymentResponse {
    @SerializedName("isSuccess")
    @Expose
    var isSuccess: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
}