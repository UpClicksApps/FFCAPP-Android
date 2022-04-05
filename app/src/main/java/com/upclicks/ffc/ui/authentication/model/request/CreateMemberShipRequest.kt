package com.upclicks.ffc.ui.authentication.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateMemberShipRequest {
    @SerializedName("emailAddress")
    @Expose
    var emailAddress: String? = null

    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("surname")
    @Expose
    var surname: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

}