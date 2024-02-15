package com.example.testapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Background extends Service {
    private static final String TAG = "MyBackgroundService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service started");

        // Perform any background tasks here

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't need to bind to this service, so we return null
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service destroyed");
    }
}