package com.upclicks.ffc.ui.products.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class ProductsResponse {

    @SerializedName("products")
    @Expose
    var products: List<Product>? = null

    @SerializedName("totalDisplayRecords")
    @Expose
    var totalDisplayRecords: Int? = null

    @SerializedName("totalRecords")
    @Expose
    var totalRecords: Int? = null

}