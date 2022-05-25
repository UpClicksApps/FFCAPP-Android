package com.upclicks.ffc.ui.checkout.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.cart.model.Cart
import com.upclicks.ffc.ui.orders.model.Order

class CheckoutRequest {
    @SerializedName("order")
    @Expose
    var order: Order? = null
    @SerializedName("orderProducts")
    @Expose
    var orderProducts: ArrayList<Cart>? = null
}