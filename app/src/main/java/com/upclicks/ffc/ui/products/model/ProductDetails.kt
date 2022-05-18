package com.upclicks.ffc.ui.products.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class ProductDetails :Product(){

    @SerializedName("reviews")
    @Expose
    var reviews: List<Review>? = null
    @SerializedName("mediaFiles")
    @Expose
    var mediaFiles: List<String>? = null
    @SerializedName("metaTags")
    @Expose
    var metaTags: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
}