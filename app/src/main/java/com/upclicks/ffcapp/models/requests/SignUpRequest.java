package com.upclicks.ffcapp.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpRequest {

    @SerializedName("EmailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Surname")
    @Expose
    private String surname;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("CityId")
    @Expose
    private String cityId;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
