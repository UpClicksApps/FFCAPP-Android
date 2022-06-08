package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChatMessages {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("lastMessage")
    @Expose
    var lastMessage: LastMessage? = null

    @SerializedName("channelName")
    @Expose
    var channelName: String? = null
    @SerializedName("messages")
    @Expose
    var messages: List<Message>? = null

    @SerializedName("channelAvatarPath")
    @Expose
    var channelAvatarPath: String? = null

    @SerializedName("channelId")
    @Expose
    var channelId: Int? = null

}