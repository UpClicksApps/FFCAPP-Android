package com.upclicks.roaa.ui.chat.data.model.messagesType

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LinkMessage{

    @SerializedName("link")
    @Expose
    var link: String? = null

}