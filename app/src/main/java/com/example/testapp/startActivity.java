package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startActivity extends AppCompatActivity {
    private Button btnAttendance , btnPurchaseRequest , btnPaymentRequest , btnTable , btnBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnAttendance = findViewById(R.id.btnAttendence);
        btnPaymentRequest = findViewById(R.id.btnPaymentRequest);
        btnPurchaseRequest = findViewById(R.id.btnPurchaseRequest);
        btnTable = findViewById(R.id.btnTable);
        btnBottomSheet = findViewById(R.id.btnBottomSheet);

        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });

        btnPurchaseRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startActivity.this , purchaseRequest.class);
                startActivity(intent);
            }
        });
        btnPaymentRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startActivity.this , PaymentRequest.class);
                startActivity(intent);
            }
        });
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startActivity.this , FirebaseDataBase.class);
                startActivity(intent);
            }
        });
        btnBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startActivity.this , BottomSheetActivity.class);
                startActivity(intent);
            }
        });
    }
}