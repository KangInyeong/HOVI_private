package com.example.hovi_android;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Update {
    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("actionNum")
    private Integer actionNum;

    @SerializedName("newAction")
    private String newAction;

    public void setDeviceId(String deviceId){
        this.deviceId = deviceId;
    }

    public void setActionNum(Integer actionNum){
        this.actionNum = actionNum;
    }

    public void setAction(String newaction){
        this.newAction = newaction;
    }
}
