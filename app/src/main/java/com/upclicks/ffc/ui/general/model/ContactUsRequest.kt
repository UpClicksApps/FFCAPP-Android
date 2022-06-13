package com.upclicks.ffc.ui.general.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContactUsRequest {
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("emailAddress")
    @Expose
    var emailAddress: String? = null

    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null


}