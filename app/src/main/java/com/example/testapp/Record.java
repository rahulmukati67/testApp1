package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Record extends AppCompatActivity {
    TextView date1;
    Button  btnComplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        date1 = findViewById(R.id.date1);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnComplete = findViewById(R.id.btnComplete);

        setDate(date1);
        btnBackClickLinear(btnBack);
        btnCompleteClickListnear(btnComplete);

    }

    private void btnBackClickLinear(ImageView btnBack){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Record.this , ConstructionJob.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void btnCompleteClickListnear(Button btnComplete){
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Record.this, "Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDate(TextView date1){
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