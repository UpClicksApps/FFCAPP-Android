package com.upclicks.ffc.ui.authentication.model.response

import com.google.gson.annotations.SerializedName

class IdentityVerificationStatus {

    @SerializedName("identityVerificationRequestDate" ) var identityVerificationRequestDate : String? = null
    @SerializedName("identityVerificationStatus"      ) var identityVerificationStatus      : Int?    = null
    @SerializedName("identityVerificationStatusText"  ) var identityVerificationStatusText  : String? = null
    @SerializedName("identityVerificationNotes"       ) var identityVerificationNotes       : String? = null
    @SerializedName("idFullName"                      ) var idFullName                      : String? = null
    @SerializedName("idNumber"                        ) var idNumber                        : String? = null
    @SerializedName("idImage"                         ) var idImage                         : String? = null
    @SerializedName("id"                              ) var id                              : Int?    = null

}