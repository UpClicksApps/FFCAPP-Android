package com.upclicks.ffc.ui.chat.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MediaFile {
    @SerializedName("mediaFilePath")
    @Expose
    val mediaFilePath: String? = null

    @SerializedName("fileType")
    @Expose
    val fileType = 0

    @SerializedName("fileName")
    @Expose
    val fileName: String? = null
}