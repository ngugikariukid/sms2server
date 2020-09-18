package com.example.sms2server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.Manifest.permission_group.CALENDAR;

public class MessageReceiver extends BroadcastReceiver {
    private static MessageListener mListener;
    String mobile,body, time;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();

        final Object[] pdus = (Object[]) data.get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String phoneNumber = smsMessage.getDisplayOriginatingAddress();
            String senderNum = phoneNumber;
            String message = smsMessage.getDisplayMessageBody();
            //long timeReceived = smsMessage.getTimestampMillis();

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(smsMessage.getTimestampMillis());
            int Day = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            String timeString = Day + " " + hour + "";
            mobile = senderNum.replaceAll("\\s", "");
            body = message.replaceAll("\\s", "+");
            time = timeString.replaceAll("\\s", "+");
            Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + body);
            // Show Alert
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context,
                    "senderNum: " + mobile + ", message: " + message + ", Time Received: " + timeString, duration);
            toast.show();
            mListener.messageReceived(mobile, message, timeString);
        }
    }

    public static void bindListener(MessageListener listener){
        mListener = listener;
    }
}
