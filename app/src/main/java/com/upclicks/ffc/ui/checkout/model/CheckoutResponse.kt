package com.upclicks.ffc.ui.checkout.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class CheckoutResponse {
    @SerializedName("resultMessage")
    @Expose
    var resultMessage: String? = null
    @SerializedName("onlinePaymenLink")
    @Expose
    var onlinePaymenLink: String? = null
    @SerializedName("orderReference")
    @Expose
    var orderReference: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("callbackUrl")
    @Expose
    var callbackUrl: String? = null
    @SerializedName("resultPageTimeoutInSec")
    @Expose
    var resultPageTimeoutInSec: Int? = null
}