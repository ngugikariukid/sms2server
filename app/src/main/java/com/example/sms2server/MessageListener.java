package com.example.sms2server;

public interface MessageListener {
    /**
     * To call this method when new message received and send back
     * @param message Message
     */
    void messageReceived(String sender, String message, String time);
}
