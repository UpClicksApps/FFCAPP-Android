package com.upclicks.ffc.ui.authentication.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateProfileRequest {
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
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("birthdate")
    @Expose
    var birthdate: String? = null
    @SerializedName("cityId")
    @Expose
    var cityId: Int? = null
    @SerializedName("gender")
    @Expose
    var gender: Int? = null
}