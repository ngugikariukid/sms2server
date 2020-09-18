package com.example.sms2server;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MessageService {
    @POST("messages")
    Call<MessageModel> createMessage(@Body MessageModel message);

    @FormUrlEncoded
    @POST("messages")
    Call<MessageModel> createMessage(@Field("sender") String sender, @Field("message") String message, @Field("time") String time);

    @FormUrlEncoded
    @POST("messages")
    Call<MessageModel> createMessage(@FieldMap Map<String, String> fields);
}
