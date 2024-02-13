package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;

public class Post_Property extends AppCompatActivity {
    ChipGroup typesChipGroup , wantChipGroup , constructionChipGroup, bhkChipGroup,bathroomChipGroup,balconyChipGroup;
    Spinner spinner;
    Chip chipResidential ,chipCommercial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property);
        typesChipGroup = findViewById(R.id.typesChipGroup);
        wantChipGroup = findViewById(R.id.wantChipGroup);
        constructionChipGroup = findViewById(R.id.constructionChipGroup);
        bhkChipGroup = findViewById(R.id.bhkChipGroup);
        bathroomChipGroup = findViewById(R.id.bathroomChipGroup);
        balconyChipGroup = findViewById(R.id.balconyChipGroup);
        spinner = findViewById(R.id.spinner);
        chipCommercial = findViewById(R.id.chipCommercial);
        chipResidential = findViewById(R.id.chipResidential);

        ArrayList<String> PropertyType = new ArrayList<>();
        PropertyType.add("Select Property Type");
        PropertyType.add("Property Type 1");
        PropertyType.add("Property Type 2");
        PropertyType.add("Property Type 3");
        final ArrayAdapter<String> Adapter = new ArrayAdapter<String>(
                getApplicationContext(),R.layout.custom_spinner,  PropertyType ){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        Adapter.setDropDownViewResource(R.layout.custom_spinner);
        spinner.setAdapter(Adapter);

        chipGroupListener(typesChipGroup);
        chipGroupListener(wantChipGroup);
        chipGroupListener(constructionChipGroup);
        chipGroupListener(bhkChipGroup);
        chipGroupListener(bathroomChipGroup);
        chipGroupListener(balconyChipGroup);
    }

    private  void chipGroupListener(ChipGroup chipGroup){
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        Toast.makeText(Post_Property.this, "You Select : " + chip.getText(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Post_Property.this, "You UnSelect : " + chip.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}