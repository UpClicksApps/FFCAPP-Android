package com.upclicks.ffc.ui.checkout.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
open class CheckoutOrder {

    @SerializedName("couponId")
    @Expose
    var couponId: String? = null
    @SerializedName("couponCode")
    @Expose
    var couponCode: String? = null
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
    @SerializedName("cityId")
    @Expose
    var cityId: String? = null
    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null
    @SerializedName("notes")
    @Expose
    var notes: String? = null
    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = null
    @SerializedName("flatNo")
    @Expose
    var flatNo: String? = null
    @SerializedName("house")
    @Expose
    var house: String? = null
    @SerializedName("deliveryTime")
    @Expose
    var deliveryTime: String? = null
    @SerializedName("street")
    @Expose
    var street: String? = null
    @SerializedName("emailAddress")
    @Expose
    var emailAddress: String? = null
    @SerializedName("memberName")
    @Expose
    var memberName: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
}