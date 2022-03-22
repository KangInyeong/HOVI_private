package com.example.hovi_android;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Action {

    @SerializedName("status")
    private Integer status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<ActionList> data;

    public String getMes(){ return message; }

    public List<ActionList> getActions(){ return data; }
//
//    public void setAction1(String action1){
//        this.action1 = action1;
//    }
//
//    public String getAction2(){
//        return action2;
//    }
//
//    public void setAction2(String action2){
//        this.action2 = action2;
//    }
}
