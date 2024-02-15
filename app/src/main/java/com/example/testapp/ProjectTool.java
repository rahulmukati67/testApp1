package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ProjectTool extends AppCompatActivity {

    CardView cardDailyLogs,cardTask,cardTimeCard,cardMaterials,cardEquipment,cardCheckList,cardGallery,cardInsights,
            cardKiosks;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_tool);
        cardDailyLogs = findViewById(R.id.cardDailyLogs);
        cardTask = findViewById(R.id.cardTask);
        cardTimeCard = findViewById(R.id.cardTimeCard);
        cardMaterials = findViewById(R.id.cardMaterials);
        cardEquipment = findViewById(R.id.cardEquipment);
        cardCheckList = findViewById(R.id.cardCheckList);
        cardGallery = findViewById(R.id.cardGallery);
        cardInsights = findViewById(R.id.cardInsights);
        cardKiosks = findViewById(R.id.cardKiosks);
        back = findViewById(R.id.btnBack);

        clickListener(cardDailyLogs);
        clickListener(cardTask);
        clickListener(cardTimeCard);
        clickListener(cardMaterials);
        clickListener(cardEquipment);
        clickListener(cardCheckList);
        clickListener(cardGallery);
        clickListener(cardInsights);
        clickListener(cardKiosks);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectTool.this, ConstructionJob.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void clickListener(CardView cardView){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectTool.this, "Clicked on : " + cardView.getContentDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}