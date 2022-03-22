package com.example.hovi_android;

import com.google.gson.annotations.SerializedName;

public class Check {

    @SerializedName("status")
    private Integer status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Boolean ismember;

    public Boolean getdata(){
        return ismember;
    }
}
