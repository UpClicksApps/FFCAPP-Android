package com.upclicks.roaa.ui.chat.data.model.messagesType

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LocationMessage{

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

}