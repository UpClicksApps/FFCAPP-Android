package com.upclicks.ffc.ui.chat.data.model

import android.text.TextUtils
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.stfalcon.chatkit.commons.models.IUser
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.commons.Keys.IS_MY_OWN
import com.upclicks.ffc.commons.Utils
import com.upclicks.ffc.ui.chat.interfaces.CustomIMessage
import java.util.*

class Message : CustomIMessage {
    @SerializedName("senderUserId")
    @Expose
    var senderUserId: String? = null

    @SerializedName("messageType")
    @Expose
    var messageType: Int? = null

    @SerializedName("id")
    @Expose
    var messageId: String? = null

    @SerializedName("message")
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

    @SerializedName("isSeen")
    @Expose
    var isSeen: Boolean? = null

    @SerializedName("files")
    @Expose
    var files: List<MediaFile>? = null

    @SerializedName("additionalData")
    @Expose
    var additionalData: Any? = null


    constructor(content: String?, isMyOwn: Boolean?) {
        this.content = content
        this.isMyOwn = isMyOwn
    }


    constructor(
        senderUserId: String?,
        content: String?,
        isMyOwn: Boolean?,
        creationTime: String?,
    ) {
        this.senderUserId = senderUserId
        this.content = content
        this.isMyOwn = isMyOwn
        this.creationTime = creationTime
        this.senderAvatar = senderAvatar
    }

    constructor(
        messageId: String?,
        senderUserId: String?,
        messageType: Int?,
        content: String?,
        creationTime: String?,
        isMyOwn: Boolean?,
        senderAvatar: String?,
        files: List<MediaFile>?,
        additionalData: Any?
    ) {
        this.messageId = messageId
        this.senderUserId = senderUserId
        this.messageType = messageType
        this.content = content
        this.creationTime = creationTime
        this.isMyOwn = isMyOwn
        this.senderAvatar = senderAvatar
        this.files = files
        this.additionalData = additionalData
    }

    constructor(
        messageId: String?,
        senderUserId: String?
    ) {
        this.messageId = messageId
        this.senderUserId = senderUserId
    }

    /**
     * Returns message identifier
     *
     * @return the message id
     */
    override fun getId(): String {
        return "" + messageId!!
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
        if (TextUtils.isEmpty(senderAvatar))
            senderAvatar = ""

        return User(
            "" + senderUserId,
            "",
            senderAvatar!!,
            false
        )
    }

    /**
     * Returns message creation date
     *
     * @return the message creation date
     */
    override fun getCreatedAt(): Date {
//        if (creationTime == null)
//            creationTime = Date().toString()
//        return Utils.convertFromStringToDate(creationTime!!)
        return Date()
    }

    override fun getImage(): String {
        if (TextUtils.isEmpty(senderAvatar))
            senderAvatar = ""
        return ""
//        return senderAvatar!!
//        return "https://myto.up-clicks.com/uploads/Vendors/3d494fc6-f415-4bc3-957c-53b0068deb71.png"
    }

    override fun getLongitude(): String {
        if (messageType != null)
            if (messageType == Keys.MessageType.ShareLocation) {
                if (additionalData != null) {
                    var longitude = additionalData as Map<String, Any>
                    return longitude?.get("longitude").toString()!!
                }
            }
        return ""
    }

    override fun getLatitude(): String {
        if (messageType == Keys.MessageType.ShareLocation) {
            if (additionalData != null) {
                var latitude = additionalData as Map<String, Any>
                return latitude?.get("latitude").toString()!!
            }
        }
        return ""
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