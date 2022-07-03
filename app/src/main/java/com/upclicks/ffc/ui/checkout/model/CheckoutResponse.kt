package com.upclicks.ffc.ui.checkout.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class CheckoutResponse {
    @SerializedName("message")
    @Expose
    var resultMessage: String? = null
    @SerializedName("onlinePaymentLink")
    @Expose
    var onlinePaymentLink: String? = null
    @SerializedName("orderReference")
    @Expose
    var orderReference: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("orderId")
    @Expose
    var orderId: String? = null
    @SerializedName("callbackUrl")
    @Expose
    var callbackUrl: String? = null
    @SerializedName("resultPageTimeoutInSec")
    @Expose
    var resultPageTimeoutInSec: Int? = null
}