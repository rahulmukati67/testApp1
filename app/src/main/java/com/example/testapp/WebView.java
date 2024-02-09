package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import com.example.testapp.databinding.ActivityWebViewBinding;
import com.google.android.material.tabs.TabLayout;
import java.util.Objects;

public class WebView extends AppCompatActivity {
    private ActivityWebViewBinding binding;
    private TabLayout tabLayout1;
    private WebViewFragment webViewFragment ;
    private Tab2 Tab2 ;
    private Tab3 Tab3 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        tabLayout1 = findViewById(R.id.TabLayout1);

        // Check if webViewFragment is null
        if (webViewFragment == null) {
            webViewFragment = new WebViewFragment(); // Create a new instance if null
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, webViewFragment);
            transaction.commit();
        }
        if (Tab2 == null) {
            Tab2 = new Tab2();
        }
        if (Tab3 == null) {
            Tab3 = new Tab3();
        }
        tabLayout1.setTabTextColors(getResources().getColorStateList(R.color.tab_text_color_selector));

        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch (Objects.requireNonNull(tab.getText()).toString()) {
                    case "Tab2":
                        fragment =  Tab2;
                        break;
                    case "Tab3":
                        fragment =  Tab3;
                        break;
                    default:
                        fragment =  webViewFragment;
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
}
