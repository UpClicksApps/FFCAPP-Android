package com.upclicks.tcare.ui.general.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JoinRequest {
    @SerializedName("providerId")
    @Expose
    var providerId: String? = null

    @SerializedName("emailAddress")
    @Expose
    var emailAddress: String? = null

    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = null

    @SerializedName("providerNameAr")
    @Expose
    var providerNameAr: String? = null

    @SerializedName("providerNameEn")
    @Expose
    var providerNameEn: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("surname")
    @Expose
    var surname: String? = null


    @SerializedName("cityId")
    @Expose
    var cityId: String? = null

}