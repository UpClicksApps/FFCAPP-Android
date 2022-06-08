package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.SerializedName

class AdConversation {
    @SerializedName("advertisementId"            ) var advertisementId            : String? = null
    @SerializedName("advertisementTitle"         ) var advertisementTitle         : String? = null
    @SerializedName("advertisementMediaFilePath" ) var advertisementMediaFilePath : String? = null
    @SerializedName("conversationsCount"                   ) var adsCount                   : Int?    = null
}