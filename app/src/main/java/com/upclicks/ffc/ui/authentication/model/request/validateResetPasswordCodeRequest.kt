package com.upclicks.ffc.ui.authentication.model.request

import com.google.gson.annotations.SerializedName

class ValidateResetPasswordCodeRequest {
    @SerializedName("accountIdentity"   ) var accountIdentity   : String? = null
    @SerializedName("passwordResetCode" ) var passwordResetCode : String? = null
}