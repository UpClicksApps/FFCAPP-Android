package com.upclicks.ffcapp.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("avatarUrl")
    @Expose
    private String avatarUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("cityName")
    @Expose
    private String cityName;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }
}
