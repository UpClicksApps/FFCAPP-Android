package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.chat.data.model.MediaFile

class UploadFilesMessage {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("conversationId")
    @Expose
    var conversationId: String? = null

    @SerializedName("senderUserId")
    @Expose
    var senderUserId: Int? = null

    @SerializedName("messageType")
    @Expose
    var messageType: Int? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("senderAvatar")
    @Expose
    var senderAvatar: String? = null

    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null

    @SerializedName("isMyOwn")
    @Expose
    var isMyOwn: Boolean? = null

    @SerializedName("files")
    @Expose
    var files: List<MediaFile>? = null

    @SerializedName("additionalData")
    @Expose
    var additionalData: Any? = null

}