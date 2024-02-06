package com.example.testapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PaymentRequest extends AppCompatActivity {
    private Spinner spinner1;
    private Spinner spinner2;
    private Button submit;
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_request);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentRequest.this, "Payment Submit Successfully✔✔", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentRequest.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<String> spinner1Items = new ArrayList<>();
        spinner1Items.add("Please Select"); spinner1Items.add("Type 1"); spinner1Items.add("Type  2") ;
        spinner1 = findViewById(R.id.spinner3);

        final ArrayAdapter<String> spinner1Adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,  spinner1Items ){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){

                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinner1Adapter);

        ArrayList<String> spinner2Items = new ArrayList<>();
        spinner2Items.add("Please Select"); spinner2Items.add("Type 1"); spinner2Items.add("Type 2") ;
        spinner2 = findViewById(R.id.spinner4);
        final ArrayAdapter<String> spinner2Adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,  spinner2Items ){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){

                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinner2Adapter);


    }
}
