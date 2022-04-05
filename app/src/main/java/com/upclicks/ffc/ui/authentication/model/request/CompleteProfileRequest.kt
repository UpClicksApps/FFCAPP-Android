package com.upclicks.ffc.ui.authentication.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CompleteProfileRequest {
    @SerializedName("cityId")
    @Expose
    var cityId: String? = null
    @SerializedName("birthDate")
    @Expose
    var birthDate: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
}