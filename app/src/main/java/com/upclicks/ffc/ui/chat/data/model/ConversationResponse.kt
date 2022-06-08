package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ConversationResponse {

    @SerializedName("receiverId")
    @Expose
    var receiverId: Int? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("receiverName")
    @Expose
    var receiverName: String? = null

    @SerializedName("receiverAvatar")
    @Expose
    var receiverAvatar: String? = null

   @SerializedName("lastMessage")
    @Expose
    var lastMessage: Message? = null


    @SerializedName("messages")
    @Expose
    var messages: List<Message>? = null

    @SerializedName("otherUserName")
    @Expose
    var otherUserName: String? = null

    @SerializedName("otherUserAvatar")
    @Expose
    var otherUserAvatar: String? = null

    @SerializedName("advertisementTitle")
    @Expose
    var advertisementTitle: String? = null

    @SerializedName("advertisementId")
    @Expose
    var advertisementId: String? = null

    @SerializedName("advertisementMediaFilePath")
    @Expose
    var advertisementMediaFilePath: String? = null
    @SerializedName("advertisementPrice")
    @Expose
    var advertisementPrice: String? = ""
    @SerializedName("advertisementAddress")
    @Expose
    var advertisementAddress: String? = null

    @SerializedName("isReadOnly")
    @Expose
    var isReadOnly :Boolean? = false
}