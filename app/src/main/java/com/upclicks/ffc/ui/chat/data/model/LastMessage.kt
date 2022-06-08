package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import com.stfalcon.chatkit.commons.models.MessageContentType
import com.upclicks.ffc.commons.Utils
import java.util.*

class LastMessage : IMessage, MessageContentType.Image {

    @SerializedName("senderUserId")
    @Expose
    var senderUserId: Int? = null

    @SerializedName("messageType")
    @Expose
    var messageType: Int? = null

    @SerializedName("additionalData")
    @Expose
    var additionalData: Any? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null

    @SerializedName("isMyOwn")
    @Expose
    var isMyOwn: Boolean? = null

    constructor(content: String?, isMyOwn: Boolean?) {
        this.content = content
        this.isMyOwn = isMyOwn
    }

    constructor(creatorUserId: Int?, content: String?, isMyOwn: Boolean?, creationTime: String?) {
        this.senderUserId = creatorUserId
        this.content = content
        this.isMyOwn = isMyOwn
        this.creationTime = creationTime
    }

    /**
     * Returns message identifier
     *
     * @return the message id
     */
    override fun getId(): String {
        return "" + senderUserId!!
    }

    /**
     * Returns message text
     *
     * @return the message text
     */
    override fun getText(): String {
        if (content == null)
            content = ""
        return content!!
    }

    /**
     * Returns message author. See the [IUser] for more details
     *
     * @return the message author
     */
    override fun getUser(): IUser {
        return User(
            "" + senderUserId,
            "",
            "https://myto.up-clicks.com/uploads/Vendors/3d494fc6-f415-4bc3-957c-53b0068deb71.png",
            false
        )
    }

    /**
     * Returns message creation date
     *
     * @return the message creation date
     */
    override fun getCreatedAt(): Date {
        if (creationTime == null)
            creationTime = Date().toString()
        return Utils.convertFromStringToDate(creationTime!!)
    }

    override fun getImageUrl(): String? {
        return "https://myto.up-clicks.com/uploads/Vendors/3d494fc6-f415-4bc3-957c-53b0068deb71.png"
    }


    internal class User(
        private val id: String,
        private val name: String,
        private val avatar: String,
        val isOnline: Boolean
    ) :
        IUser {

        override fun getId(): String {
            return id
        }

        override fun getName(): String {
            return name
        }

        override fun getAvatar(): String {
            return avatar
        }

    }

}