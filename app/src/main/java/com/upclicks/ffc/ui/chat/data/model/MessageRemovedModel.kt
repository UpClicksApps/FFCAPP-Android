package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageRemovedModel {

    @SerializedName("message_id")
    @Expose
    var message_id: String? = null
    @SerializedName("conversation_id")
    @Expose
    var conversation_id: String? = null

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
}