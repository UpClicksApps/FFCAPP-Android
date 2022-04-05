package com.upclicks.ffc.ui.general.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class Category : BaseSelection() {

    @SerializedName("mediaFilePath")
    @Expose
    var mediaFilePath: String? = null

}