package com.example.hovi_android;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class UpdateResponse {
    @SerializedName("status")
    private Integer status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private String data;

    public String getMes(){
        return message;
    }

}
