package com.upclicks.ffc.ui.products.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class ProductRequest {
    @SerializedName("categoryId")
    @Expose
    var categoryId: String? = ""
    @SerializedName("productName")
    @Expose
    var productName: String? = ""
    @SerializedName("minPrice")
    @Expose
    var minPrice: Float? = 0f
    @SerializedName("maxPrice")
    @Expose
    var maxPrice: Float? = 0f
    @SerializedName("skip")
    @Expose
    var skip: Int? = 0
    @SerializedName("take")
    @Expose
    var take: Int? = 10
    @SerializedName("sortProductBy")
    @Expose
    var sortProductBy: Int? = 0
}