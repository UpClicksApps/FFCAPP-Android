package com.upclicks.ffc.ui.products.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection

class Review{
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("reviewText")
    @Expose
    var reviewText: String? = null
    @SerializedName("memberName")
    @Expose
    var memberName: String? = null
    @SerializedName("memberId")
    @Expose
    var memberId: String? = null
    @SerializedName("memberAvatarPath")
    @Expose
    var memberAvatarPath: String? = null
    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null
    @SerializedName("rate")
    @Expose
    var rate: Int? = null
}