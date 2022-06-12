package com.upclicks.ffc.ui.orders.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
open class Order {
    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("paymentWayText")
    @Expose
    var paymentWayText: String? = null
    @SerializedName("deliveryWayText")
    @Expose
    var deliveryWayText: String? = null
    @SerializedName("orderDate")
    @Expose
    var orderDate: String? = null
    @SerializedName("orderStatus")
    @Expose
    var orderStatus: Int? = null
    @SerializedName("orderStatusText")
    @Expose
    var orderStatusText: String? = null
    @SerializedName("deliveryFees")
    @Expose
    var deliveryFees: Double? = null
    @SerializedName("discountAmount")
    @Expose
    var discountAmount: Double? = null
    @SerializedName("net")
    @Expose
    var net: Double? = null
    @SerializedName("subTotal")
    @Expose
    var subTotal: Double? = null
    @SerializedName("totalCost")
    @Expose
    var totalCost: Double? = null
    @SerializedName("paymentWay")
    @Expose
    var paymentWay: Int? = null
    @SerializedName("discount")
    @Expose
    var discount: Int? = null
    @SerializedName("deliveryWay")
    @Expose
    var deliveryWay: Int? = null
    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
}