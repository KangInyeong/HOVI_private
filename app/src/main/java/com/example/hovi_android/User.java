package com.example.hovi_android;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;

public class User {

    @SerializedName("status")
    private Integer status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private UserData data;

    class UserData{
        @SerializedName("createdAt")
        private String createdAt;

        @SerializedName("updatedAt")
        private String updatedAt;

        @SerializedName("id")
        private Long id;

        @SerializedName("deviceId")
        private String deviceId;

        public String getdid(){
            return deviceId;
        }

    }
//
//    public UserData getData(){
//        return data;
//    }

    public Integer getStatus(){
        return status;
    }


}
