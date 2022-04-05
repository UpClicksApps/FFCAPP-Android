package com.upclicks.ffc.ui.authentication.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MembershipResponse {
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("accountIdentity")
    @Expose
    var membershipIdentity: String? = null
}