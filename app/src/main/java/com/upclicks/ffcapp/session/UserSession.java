package com.upclicks.ffcapp.session;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.upclicks.ffcapp.models.response.Profile;


public class UserSession {
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("profile")
    @Expose
    private Profile profile;

    public String getAccessToken() {
        return accessToken;
    }

    public Profile getProfile() {
        return profile;
    }
}
