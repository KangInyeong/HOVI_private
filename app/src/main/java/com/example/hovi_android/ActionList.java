package com.example.hovi_android;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;

public class ActionList {

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("id")
    private Long id;

    @SerializedName("userId")
    private String userId;

    @SerializedName("actionNum")
    private String actionNum;

    @SerializedName("actionBody")
    private String actionBody;

    public String getActionNum(){
        return actionNum;
    }

    public String getActionBody(){
        return actionBody;
    }

}
