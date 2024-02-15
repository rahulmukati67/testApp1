package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Record extends AppCompatActivity {
    TextView date1, dateSunday, dateMonday, dateTuesday, dateWednesday, dateThursday, dateFriday, dateSaturday;
    Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        date1 = findViewById(R.id.date1);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnComplete = findViewById(R.id.btnComplete);
        dateSaturday = findViewById(R.id.dateSaturday);
        dateSunday = findViewById(R.id.dateSunday);
        dateMonday = findViewById(R.id.dateMonday);
        dateTuesday = findViewById(R.id.dateTuesday);
        dateWednesday = findViewById(R.id.dateWednesday);
        dateThursday = findViewById(R.id.dateThursday);
        dateFriday = findViewById(R.id.dateFriday);

        setDate(date1);
        btnBackClickLinear(btnBack);
        btnCompleteClickLinear(btnComplete);
        setWeekDates();

    }

    private void btnBackClickLinear(ImageView btnBack) {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Record.this, ConstructionJob.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void btnCompleteClickLinear(Button btnComplete) {
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Record.this, "Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDate(TextView date1) {
        Date date = new Date();
        LocalDateTime localDateTime = null;
        String formattedDate = null;
        DateTimeFormatter formatter = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
            formattedDate = localDateTime.format(formatter);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
            formattedDate = sdf.format(date);
        }
        date1.setText(formattedDate);
    }

    private void setWeekDates() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

            dateMonday.setText(startOfWeek.format(DateTimeFormatter.ofPattern("dd")));
            dateTuesday.setText(startOfWeek.plusDays(1).format(DateTimeFormatter.ofPattern("dd")));
            dateWednesday.setText(startOfWeek.plusDays(2).format(DateTimeFormatter.ofPattern("dd")));
            dateThursday.setText(startOfWeek.plusDays(3).format(DateTimeFormatter.ofPattern("dd")));
            dateFriday.setText(startOfWeek.plusDays(4).format(DateTimeFormatter.ofPattern("dd")));
            dateSaturday.setText(startOfWeek.plusDays(5).format(DateTimeFormatter.ofPattern("dd")));
            dateSunday.setText(startOfWeek.plusDays(-1).format(DateTimeFormatter.ofPattern("dd")));

            LocalDate currentDate = LocalDate.now();
            TextView[] dateTextViews = {dateSunday, dateMonday, dateTuesday, dateWednesday, dateThursday, dateFriday, dateSaturday};
            for (TextView textView : dateTextViews) {
                if (textView.getText().toString().equals(String.valueOf(currentDate.getDayOfMonth()))) {
                    textView.setTextColor(Color.WHITE);
                    textView.setBackgroundResource(R.drawable.circuler_bg);
                    break;
                }
            }
        }
    }
}