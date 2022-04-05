package com.upclicks.ffc.ui.notification.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Notification {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("notificationImage")
    @Expose
    var notificationImage: String? = null

    @SerializedName("notificationName")
    @Expose
    var notificationName: String? = null

    @SerializedName("notificationState")
    @Expose
    var notificationState: Int? = null


    @SerializedName("notificationType")
    @Expose
    var notificationType: Int? = null

    @SerializedName("entityId")
    @Expose
    var entityId: String? = null

    @SerializedName("subEntityId")
    @Expose
    var subEntityId: String? = null

}