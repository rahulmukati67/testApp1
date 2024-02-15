package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startActivity extends AppCompatActivity {
    private Button btnAttendance , btnPurchaseRequest , btnConstructionJob,btnLogin,btnMyWallet,btnPostProperty,btnDatePicker,btnPaymentRequest , btnTable , btnBottomSheet ,btnWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnAttendance = findViewById(R.id.btnAttendence);
        btnPaymentRequest = findViewById(R.id.btnPaymentRequest);
        btnPurchaseRequest = findViewById(R.id.btnPurchaseRequest);
        btnTable = findViewById(R.id.btnTable);
        btnBottomSheet = findViewById(R.id.btnBottomSheet);
        btnWebView = findViewById(R.id.btnWebView);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnPostProperty= findViewById(R.id.btnPostProperty);
        btnMyWallet = findViewById(R.id.btnMyWallet);
        btnConstructionJob = findViewById(R.id.btnConstructionJob);
//        btnLogin =  findViewById(R.id.btnLogin);

        ButtonClick(btnAttendance ,  MainActivity.class);
        ButtonClick(btnPurchaseRequest ,  purchaseRequest.class);
        ButtonClick(btnPaymentRequest , PaymentRequest.class);
        ButtonClick(btnTable , FirebaseDataBase.class);
        ButtonClick(btnBottomSheet ,  BottomSheetActivity.class);
        ButtonClick(btnWebView ,  WebView.class);
        ButtonClick(btnDatePicker ,  DatePicker.class);
        ButtonClick(btnPostProperty , Post_Property.class);
        ButtonClick(btnMyWallet , MyWallet.class);
        ButtonClick(btnConstructionJob , ConstructionJob.class);

    }

    private void ButtonClick(Button btn , Class<?> target){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startActivity.this  ,target);
                startActivity(intent);
            }
        });
    }
}