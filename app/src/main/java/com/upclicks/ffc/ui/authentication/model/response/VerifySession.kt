package com.upclicks.ffc.ui.authentication.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifySession {
    @SerializedName("sessionStatus")
    @Expose
    var sessionStatus: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("profile")
    @Expose
    var profile: Profile? = null
    @SerializedName("isInNormalMode")
    @Expose
    var isInNormalMode: Boolean? = false

    class Profile {
        @SerializedName("genderEnum"                     ) var genderEnum                     : Int?     = null
        @SerializedName("gender"                         ) var gender                         : String?  = null
        @SerializedName("identityVerificationStatus"     ) var identityVerificationStatus     : String?  = null
        @SerializedName("identityVerificationStatusEnum" ) var identityVerificationStatusEnum : Int?     = null
        @SerializedName("unSeenNotificationsCount"       ) var unSeenNotificationsCount       : Int?     = null
        @SerializedName("unSeenMessagesCount"            ) var unSeenMessagesCount            : Int?     = null
        @SerializedName("currentCartProductsCount"            ) var currentCartProductsCount            : Int?     = null
        @SerializedName("isProfileCompleted"             ) var isProfileCompleted             : Boolean? = null
        @SerializedName("userId"                         ) var userId                         : Int?     = null
        @SerializedName("avaterPath"                     ) var avatarPath                     : String?  = null
        @SerializedName("fullName"                       ) var fullName                       : String?  = null
        @SerializedName("name"                           ) var name                           : String?  = null
        @SerializedName("surname"                        ) var surname                        : String?  = null
        @SerializedName("countryId"                      ) var countryId                      : Int?     = null
        @SerializedName("countryName"                    ) var countryName                    : String?  = null
        @SerializedName("cityId"                         ) var cityId                         : String?     = null
        @SerializedName("governorateId"                 ) var governorateId                         : String?     = null
        @SerializedName("cityName"                       ) var cityName                       : String?  = null
        @SerializedName("governorateName"                ) var governorateName                       : String?  = null
        @SerializedName("emailAddress"                   ) var emailAddress                   : String?  = null
        @SerializedName("birthDate"                      ) var birthDate                   : String?  = null
        @SerializedName("address"                        ) var address                   : String?  = null
        @SerializedName("phoneNumber"                    ) var phoneNumber                    : String?  = null
        @SerializedName("id"                             ) var id                             : String?     = null
    }

}