package com.upclicks.ffc.ui.orders

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.orders.model.CheckoutOrder
import com.upclicks.ffc.ui.orders.model.Order
import com.upclicks.ffc.ui.products.model.Product

class OrderDetails: Order() {
    @SerializedName("memberId")
    @Expose
    var memberId: String? = null
    @SerializedName("couponId")
    @Expose
    var couponId: String? = null
    @SerializedName("couponCode")
    @Expose
    var couponCode: String? = null
    @SerializedName("cityName")
    @Expose
    var cityName: String? = null
    @SerializedName("notes")
    @Expose
    var notes: String? = null
    @SerializedName("onlinePaymentRef")
    @Expose
    var onlinePaymentRef: String? = null
    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = null
    @SerializedName("emailAddress")
    @Expose
    var emailAddress: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("street")
    @Expose
    var street: String? = null
    @SerializedName("houseNumber")
    @Expose
    var houseNumber: String? = null
    @SerializedName("deliveryTime")
    @Expose
    var deliveryTime: String? = null
    @SerializedName("memberName")
    @Expose
    var memberName: String? = null
    @SerializedName("orderProducts")
    @Expose
    var orderProducts: List<Product>? = null

}