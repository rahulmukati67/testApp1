package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class purchaseRequest extends AppCompatActivity implements ProjectWise.LinearLayoutListener {
     private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_request);
        View rootView = findViewById(R.id.mainPurchaseRequest);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                for (Fragment fragment : fragmentManager.getFragments()) {
                    if (fragment instanceof ProjectWise) {
                        ((ProjectWise) fragment).setLinearLayoutListener(purchaseRequest.this);
                        ((ProjectWise) fragment).notifyLinearLayoutHidden();
                    }
                }
                return false;
            }
        });
         tabLayout = findViewById(R.id.TabLayout1);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new ProjectWise());
        transaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TabLayout", "Tab Selected: " + tab.getText());

                Fragment fragment;
                switch (Objects.requireNonNull(tab.getText()).toString()) {
                    case "PROJECT WISE":
                        fragment = new ProjectWise();
                        Log.d("Fragment", "Fragment Called Project wise " );
                        break;
                    case "MATERIAL WISE":
                        fragment = new MaterialWise();
                        Log.d("Fragment", "Fragment Called Material wise " );
                        break;
                    case "REPORT":
                        fragment = new Report();
                        Log.d("Fragment", "Fragment Called REPORT " );
                        break;
                    default:
                        fragment = new Report();
                        Log.d("Fragment", "Fragment Called Report() " );
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void hideLinearLayout() {
        LinearLayout linearLayout = findViewById(R.id.linerLayout1);
        LinearLayout MaterialNameLinearLayout = findViewById(R.id.MaterialNameLinearLayout);
        if (linearLayout.getVisibility() == View.VISIBLE) {
            linearLayout.setVisibility(View.GONE);
        }
        if (MaterialNameLinearLayout.getVisibility() == View.VISIBLE) {
            MaterialNameLinearLayout.setVisibility(View.GONE);
        }
    }
}