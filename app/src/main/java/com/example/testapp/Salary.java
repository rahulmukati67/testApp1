package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;


public class Salary extends AppCompatActivity {
    ChipGroup chipJan12,chipJan13,chipJan14,chipJan15;
    Button check;
    TextView txtSalary ,count;
    int counter =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        chipJan12 = findViewById(R.id.chipJan12);
        chipJan13 = findViewById(R.id.chipJan13);
        chipJan14 = findViewById(R.id.chipJan14);
        chipJan15 = findViewById(R.id.chipJan15);
        check = findViewById(R.id.check);
        count=findViewById(R.id.count);
        txtSalary = findViewById(R.id.txtSalary);

        setColour(chipJan12);
        setColour(chipJan13);
        setColour(chipJan14);
        setColour(chipJan15);
        List<ChipGroup> chipGroups = new ArrayList<>();
        chipGroups.add(chipJan12);
        chipGroups.add(chipJan13);
        chipGroups.add(chipJan14);
        chipGroups.add(chipJan15);

        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean allGroupsSelected = true;
                for (int i = 0; i < chipGroups.size(); i++) {
                    ChipGroup chipGroup = chipGroups.get(i);
                    boolean chipSelected = false;
                    for (int j = 0; j < chipGroup.getChildCount(); j++) {
                        Chip chip = (Chip) chipGroup.getChildAt(j);
                        if (chip.isChecked()) {
                            chipSelected = true;
                            break;
                        }
                    }
                    if (!chipSelected) {
                        allGroupsSelected = false;
                        Toast.makeText(getApplicationContext(), "Please Mark All Days Attendance ", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if (allGroupsSelected) {
                    calculateSalary();
                }
            }
        });
    }

    private void calculateSalary(){
        count.setText(String.valueOf(counter));
        int salary = counter*600;
        txtSalary.setText(String.valueOf(salary));
    }

    private  void setColour(ChipGroup chipGroup ){
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked && chip.getText().toString().equals("Present")&&chip.getCurrentTextColor() != Color.GREEN) {
                            chip.setTextColor(Color.GREEN);
                            counter++;

                    }else if(isChecked && chip.getText().toString().equals("Absent") &&(chip.getCurrentTextColor() != Color.RED)) {
                            chip.setTextColor(Color.RED);
                    }else{
                        chip.setTextColor(Color.BLACK);
                        if(chip.getText().toString().equals("Present")) {
                            counter--;
                        }
                    }
                }
            });
        }
    }
}