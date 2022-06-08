package com.upclicks.ffc.ui.chat.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocketMessageResponse {
    @SerializedName("nameValuePairs")
    @Expose
    private NamePairsMessage nameValuePairs;

    public NamePairsMessage getNameValuePairs() {
        return nameValuePairs;
    }

    public void setNameValuePairs(NamePairsMessage nameValuePairs) {
        this.nameValuePairs = nameValuePairs;
    }
}
