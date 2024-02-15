package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConstructionJob extends AppCompatActivity {
    private Button btnTools,btnTrackMaterial,btnSalary,btnRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction_job);
        btnTools = findViewById(R.id.btnTools);
        btnTrackMaterial= findViewById(R.id.btnTrackMaterial);
        btnSalary = findViewById(R.id.btnSalary);
        btnRecord = findViewById(R.id.btnRecord);

        ButtonClick(btnTools ,  ProjectTool.class);
        ButtonClick(btnTrackMaterial , TrackMaterial.class);
        ButtonClick(btnSalary , Salary.class);
        ButtonClick(btnRecord ,Record.class);

    }

    private void ButtonClick(Button btn , Class<?> target){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConstructionJob.this  ,target);
                startActivity(intent);
            }
        });
    }
}