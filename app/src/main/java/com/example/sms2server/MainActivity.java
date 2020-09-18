package com.example.sms2server;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MessageListener {
    private MessagesRepository messagesRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Register sms listener
        MessageReceiver.bindListener(this);
        messagesRepository = messagesRepository.getInstance();
    }

    @Override
    public void messageReceived(String sender, String message, String time) {
        Toast.makeText(this, "New Message Received: " + message +" From: "+ sender +" and Time Received: "+ time, Toast.LENGTH_SHORT).show();

        MessageModel c = new MessageModel(
                sender, message, time
        );

        messagesRepository.getMessagesService().createMessage(c).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> r) {
                Toast.makeText(getApplicationContext(), "Message " + r.body().getMessage() + " created", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Creating Message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
