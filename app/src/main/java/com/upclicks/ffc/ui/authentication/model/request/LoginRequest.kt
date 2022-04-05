package com.upclicks.ffc.ui.authentication.model.request

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class LoginRequest {
    @SerializedName("UsernameOrEmailAddress")
    @Expose
    var usernameOrEmailAddress: String? = null

    @SerializedName("Password")
    @Expose
    var password: String? = null

    @SerializedName("rememberClient")
    @Expose
    var rememberClient: Boolean? = null

}