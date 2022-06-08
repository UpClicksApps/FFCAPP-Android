package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.SerializedName


class Chat {

    @SerializedName("unseenMessagesCount" ) var unseenMessagesCount : Int?         = null
    @SerializedName("otherUser"           ) var otherUser           : OtherUser?   = OtherUser()
    @SerializedName("conversationId"      ) var conversationId      : String?      = null
    @SerializedName("lastMessage"         ) var lastMessage         : LastMessage? = LastMessage()

   data class Conversation (
       @SerializedName("isReadOnly"          ) var isReadOnly          : Boolean?   = null,
       @SerializedName("otherUser"           ) var otherUser           : OtherUser? = OtherUser(),
       @SerializedName("unseenMessagesCount" ) var unseenMessagesCount : Int?       = null,
       @SerializedName("id"                  ) var id                  : String?    = null,
       @SerializedName("chatSubscriptionDescription"           ) var chatSubscriptionDescription           : String?  = null
   )

    data class LastMessage (
        @SerializedName("message"      ) var message      : String?  = null,
        @SerializedName("creationTime" ) var creationTime : String?  = null,
        @SerializedName("isSeen"       ) var isSeen       : Boolean? = null,
        @SerializedName("isMyOwn"      ) var isMyOwn      : Boolean? = null
    )

    data class OtherUser (
        @SerializedName("name"   ) var name   : String? = null,
        @SerializedName("avatar" ) var avatar : String? = null,
        @SerializedName("id"     ) var id     : Int?    = null
    )
}
