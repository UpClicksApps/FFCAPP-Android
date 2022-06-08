package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

class NamePairsMessage {
    @SerializedName("senderUserId")
    @Expose
    var senderUserId: Int? = null
    @SerializedName("messageType")
    @Expose
    var messageType: Int? = null

    @SerializedName("files")
    @Expose
    var files: JSONArray? = null

    @SerializedName("message")
    @Expose
    var content: String? = null

    @SerializedName("id")
    @Expose
    var messageId: String? = null

    @SerializedName("senderAvatar")
    @Expose
    var senderAvatar: String? = null

    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null
    @SerializedName("additionalData")
    @Expose
    var additionalData: JSONObject? = null

    @SerializedName("isMyOwn")
    @Expose
    var isMyOwn: Boolean? = null
    @SerializedName("conversationId")
    @Expose
    var conversion_id: String? = null
}