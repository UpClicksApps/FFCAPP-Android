package com.upclicks.ffc.ui.cart.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.products.model.Product

class CartDetails {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("discountAmount")
    @Expose
    var discountAmount: Double? = null
    @SerializedName("net")
    @Expose
    var total: Double? = null
    @SerializedName("totalCost")
    @Expose
    var subTotal: Double? = null
    @SerializedName("orderProducts")
    @Expose
    var orderProducts: List<Cart>? = null

}