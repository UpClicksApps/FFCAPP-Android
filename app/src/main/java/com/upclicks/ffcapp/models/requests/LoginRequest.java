package com.upclicks.ffcapp.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("UsernameOrEmailAddress")
    @Expose
    private String usernameOrEmailAddress;
    @SerializedName("Password")
    @Expose
    private String password;

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
