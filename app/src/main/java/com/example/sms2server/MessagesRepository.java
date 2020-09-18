package com.example.sms2server;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class MessagesRepository {
    private static MessagesRepository instance;

    private MessageService messageService;

    public static MessagesRepository getInstance() {
        if (instance == null) {
            instance = new MessagesRepository();
        }
        return instance;
    }
    public MessagesRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:80/Sms2Server/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        messageService = retrofit.create(MessageService.class);
    }
    public MessageService getMessagesService() {
        return messageService;
    }
}
