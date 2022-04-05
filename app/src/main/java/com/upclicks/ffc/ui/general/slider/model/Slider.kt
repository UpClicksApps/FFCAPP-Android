package com.upclicks.ffc.ui.general.slider.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Slider {
    @SerializedName("imagePath")
    @Expose
    var imagePath: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("subTitle")
    @Expose
    var subTitle: String? = null
    
}