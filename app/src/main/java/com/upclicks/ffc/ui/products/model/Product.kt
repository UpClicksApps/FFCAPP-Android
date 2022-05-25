package com.upclicks.ffc.ui.products.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

open class Product{

    @SerializedName("defaultMediaFilePath")
    @Expose
    var defaultMediaFilePath: String? = null
    @SerializedName("ordersCount")
    @Expose
    var ordersCount: Int? = null
    @SerializedName("productCountInCart")
    @Expose
    var productCountInCart: Int? = null
    @SerializedName("discountPercentage")
    @Expose
    var discountPercentage: Int? = null
    @SerializedName("averageRate")
    @Expose
    var averageRate: Double? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null
    @SerializedName("salePrice")
    @Expose
    var salePrice: Double? = null
    @SerializedName("currentPrice")
    @Expose
    var currentPrice: Double? = null
    @SerializedName("isOnMyWishlist")
    @Expose
    var isOnMyWishlist: Boolean? = null
    @SerializedName("isNew")
    @Expose
    var isNew: Boolean? = null
    @SerializedName("isOnSale")
    @Expose
    var isOnSale: Boolean? = null
    @SerializedName("isOutOfStock")
    @Expose
    var isOutOfStock: Boolean? = null
    @SerializedName("categoryName")
    @Expose
    var categoryName: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null

}