package com.example.testapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class sample extends AppCompatActivity {

    private TextInputEditText message, number;
    private MaterialTextView currentNumber;
    Button btnsend;

    private static final int PERMISSION_REQUEST_CODE = 1;

    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_NUMBER = "saved_number";

    private final BroadcastReceiver smsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < pdus.length; i++) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            String format = bundle.getString("format");
                            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                        }
                    }
                    processReceivedMessages(messages);

                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        message = findViewById(R.id.message);
        number = findViewById(R.id.number);
        currentNumber = findViewById(R.id.currentNumber);
        btnsend = findViewById(R.id.btnSend);

        registerSmsReceiver();
        requestSmsSendAndStoragePermission();

        String savedNumber = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getString(KEY_NUMBER, "");
        if (!TextUtils.isEmpty(savedNumber)) {
            currentNumber.setText(savedNumber);
            number.setText(savedNumber);
        }
        sendMessage();
    }

     private void processReceivedMessages(SmsMessage[] messages) {
        for (SmsMessage message : messages) {
        String sender = message.getDisplayOriginatingAddress();
        String messageBody = message.getMessageBody();
//        String savedNumber = currentNumber.getText().toString();
        Toast.makeText(this, "Received SMS: " + messageBody, Toast.LENGTH_LONG).show();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+917225936062", null, messageBody, null, null);
            Toast.makeText(sample.this, "Sending another message ", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendMessage() {
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = Objects.requireNonNull(number.getText()).toString();
                String msg = Objects.requireNonNull(message.getText()).toString();

                if (num.length() < 10 || msg.length() == 0) {
                    Toast.makeText(sample.this, "Please Enter Valid Message / Number", Toast.LENGTH_LONG).show();
                } else {

                    getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                            .edit()
                            .putString(KEY_NUMBER, num)
                            .apply();

                    currentNumber.setText(num);

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+91" + num, null, msg, null, null);
                    Toast.makeText(sample.this, "Sending", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerSmsReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
    }
    private void requestSmsSendAndStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.RECEIVE_SMS}, PERMISSION_REQUEST_CODE);
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(smsReceiver);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            registerSmsReceiver();
        } else {
            Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}


