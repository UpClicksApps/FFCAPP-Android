package com.upclicks.ffc.ui.orders.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
open class OrderStatusLog {
    @SerializedName("creatorUserName")
    @Expose
    var creatorUserName: String? = null
    @SerializedName("orderStatusText")
    @Expose
    var orderStatusText: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("orderStatus")
    @Expose
    var orderStatus: String? = null
    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
}