package com.example.sms2server;

import com.google.gson.annotations.SerializedName;

public class MessageModel {
    @SerializedName("sender")
    private String sender;
    @SerializedName("message")
    private String message;
    @SerializedName("time")
    private String time;

    public MessageModel(String sender, String message, String time) {
        this.sender = sender;
        this.message = message;
        this.time = time;
    }
    public String getSender() {
        return sender;
    }
    public String getMessage() {
        return message;
    }
    public String getTime() {
        return time;
    }

}
