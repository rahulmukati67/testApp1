package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class purchaseRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_request);
        TabLayout tabLayout = findViewById(R.id.TabLayout1);

       TabLayout.Tab first = tabLayout.getTabAt(0);
        if(first != null){
            first.select();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new ProjectWise());
        transaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ( tab.getText().toString().equals("PROJECT WISE")) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new ProjectWise());
                    transaction.commit();
                }
                if ( tab.getText().toString().equals("MATERIAL WISE")) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new MaterialWise());
                    transaction.commit();
                }
                if (tab.getText().toString().equals("REPORT")) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new Report());
                    transaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });
    }
}