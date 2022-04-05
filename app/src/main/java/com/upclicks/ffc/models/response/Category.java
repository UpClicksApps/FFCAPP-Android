package com.upclicks.ffc.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageName")
    @Expose
    private String imageName;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("ico")
    @Expose
    private String ico;
    @SerializedName("icoPath")
    @Expose
    private String icoPath;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getIco() {
        return ico;
    }

    public String getIcoPath() {
        return icoPath;
    }

    public Integer getId() {
        return id;
    }
}
