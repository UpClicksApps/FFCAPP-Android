package com.upclicks.ffc.ui.authentication.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifyIDRequest {
    @SerializedName("fullName")
    @Expose
    var fullName: String? = null
    @SerializedName("idNumber")
    @Expose
    var idNumber: String? = null
}