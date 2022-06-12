package com.upclicks.ffc.socket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseSocketResponseModel<T> {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("result")
    @Expose
    private T result;

    @SerializedName("error")
    @Expose
    private T error;

    public Boolean getSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

}