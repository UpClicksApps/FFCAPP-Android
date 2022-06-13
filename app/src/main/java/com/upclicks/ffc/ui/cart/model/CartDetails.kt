package com.upclicks.ffc.ui.cart.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.products.model.Product

class CartDetails {

    @SerializedName("hasCoupon")
    @Expose
    var hasCoupon: String? = null
    @SerializedName("couponId")
    @Expose
    var couponId: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("discountAmount")
    @Expose
    var discountAmount: Double? = null
    @SerializedName("total")
    @Expose
    var total: Double? = null
    @SerializedName("subTotal")
    @Expose
    var subTotal: Double? = null
    @SerializedName("orderProducts")
    @Expose
    var orderProducts: List<Cart>? = null

}