package com.upclicks.ffc.ui.authentication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Session (
    @SerializedName("accessToken")
    @Expose
    var accessToken: String? = null,
    @SerializedName("expireInSeconds")
    @Expose
    var expireInSeconds: String? = null,
    @SerializedName("encryptedAccessToken")
    @Expose
    var encryptedAccessToken: String? = null,
    @SerializedName("userId")
    @Expose
    var userId: String? = null
)