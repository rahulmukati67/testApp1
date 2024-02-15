package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class TrackMaterial extends AppCompatActivity {

    MaterialTextView month , date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_material);
        month = findViewById(R.id.month);
        date = findViewById(R.id.date);

        DateTimeFormatter dateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now();
            dateFormat = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MMMM");
            date.setText(dateFormat.format(now));
            month.setText(monthFormat.format(now));
        }

    }
}