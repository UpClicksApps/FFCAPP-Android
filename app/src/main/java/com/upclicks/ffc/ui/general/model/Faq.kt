package com.upclicks.ffc.ui.general.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Faq {
    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("answer")
    @Expose
    var answer: String? = null

    @SerializedName("creationTime")
    @Expose
    var creationTime: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null


}