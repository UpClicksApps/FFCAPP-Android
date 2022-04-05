package com.upclicks.ffc.ui.authentication.model.request

import com.google.gson.annotations.SerializedName

class ChangePasswordRequest {
    @SerializedName("currentPassword" ) var currentPassword : String? = null
    @SerializedName("newPassword"     ) var newPassword     : String? = null
}