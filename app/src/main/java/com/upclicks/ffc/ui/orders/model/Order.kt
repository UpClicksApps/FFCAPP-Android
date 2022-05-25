package com.upclicks.ffc.ui.orders.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
class Order {
    @SerializedName("couponId")
    @Expose
    var couponId: String? = null
    @SerializedName("couponCode")
    @Expose
    var couponCode: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("voucherId")
    @Expose
    var voucherId: String? = null
    @SerializedName("providerSpecialistId")
    @Expose
    var providerSpecialistId: String? = null
    @SerializedName("providerId")
    @Expose
    var providerId: String? = null
    @SerializedName("orderDate")
    @Expose
    var orderDate: String? = null
    @SerializedName("servicePlace")
    @Expose
    var servicePlace: Int? = null
    @SerializedName("deliveryFees")
    @Expose
    var deliveryFees: Double? = null
    @SerializedName("totalCost")
    @Expose
    var totalCost: Double? = null
    @SerializedName("paymentWay")
    @Expose
    var paymentWay: Int? = null
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
    @SerializedName("emailAddress")
    @Expose
    var emailAddress: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("memberName")
    @Expose
    var memberName: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
}