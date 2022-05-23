package com.upclicks.ffc.ui.cart.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class Cart {

    @SerializedName("mediaFilePath")
    @Expose
    var mediaFilePath: String? = null

}