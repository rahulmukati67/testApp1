package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Record extends AppCompatActivity {
    TextView date1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        date1 = findViewById(R.id.date1);

       Date date = new Date();
        LocalDateTime localDateTime = null;
        String formattedDate = null;
        DateTimeFormatter formatter = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
            formattedDate = localDateTime.format(formatter);
        } else{
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
            formattedDate = sdf.format(date);
        }
        date1.setText(formattedDate);

    }
}