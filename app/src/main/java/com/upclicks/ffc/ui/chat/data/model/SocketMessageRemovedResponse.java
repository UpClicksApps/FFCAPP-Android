package com.upclicks.ffc.ui.chat.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocketMessageRemovedResponse {
    @SerializedName("nameValuePairs")
    @Expose
    private MessageRemovedModel nameValuePairs;

    public MessageRemovedModel getNameValuePairs() {
        return nameValuePairs;
    }

    public void setNameValuePairs(MessageRemovedModel nameValuePairs) {
        this.nameValuePairs = nameValuePairs;
    }
}
