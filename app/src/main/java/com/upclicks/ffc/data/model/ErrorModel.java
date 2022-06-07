package com.upclicks.ffc.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.upclicks.ffc.commons.Utils;

public class ErrorModel {
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;

    public ErrorModel() {
    }

    public ErrorModel(String message) {
        this.message = message;
    }

    public String getDetails() {
        return Utils.Companion.isNullOrEmpty(details)?"":details;
    }

    public String getMessage() {
        return Utils.Companion.isNullOrEmpty(message)?"":message;
    }

    public Integer getCode() {
        return code;
    }
}
