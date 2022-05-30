package com.upclicks.ffc.ui.checkout.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.cart.model.Cart
import com.upclicks.ffc.ui.orders.model.CheckoutOrder

class CheckoutRequest {
    @SerializedName("checkoutOrder")
    @Expose
    var checkoutOrder: CheckoutOrder? = null
    @SerializedName("orderProducts")
    @Expose
    var orderProducts: ArrayList<Cart>? = null
}