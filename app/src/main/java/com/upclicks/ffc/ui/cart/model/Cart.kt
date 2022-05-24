package com.upclicks.ffc.ui.cart.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class Cart {

    @SerializedName("defaultMediaFilePath")
    @Expose
    var defaultMediaFilePath: String? = null
    @SerializedName("quantity")
    @Expose
    var quantity: Int? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null
    var isOnMyWishlist: Boolean? = null
    @SerializedName("isOutOfStock")
    @Expose
    var isOutOfStock: Boolean? = null
    @SerializedName("categoryName")
    @Expose
    var categoryName: String? = null
    @SerializedName("productName")
    @Expose
    var productName: String? = null
    @SerializedName("productId")
    @Expose
    var productId: String? = null

}