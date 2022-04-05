package com.upclicks.ffc.ui.authentication.model.request

import com.google.gson.annotations.SerializedName

class ResetPasswordRequest {
    @SerializedName("accountIdentity"   ) var accountIdentity   : String? = null
    @SerializedName("passwordResetCode" ) var passwordResetCode : String? = null
    @SerializedName("newPassword"       ) var newPassword       : String? = null
}