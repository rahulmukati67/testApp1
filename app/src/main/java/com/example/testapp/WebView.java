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
    private WebViewFragment webViewFragment; // Member variable to hold the WebViewFragment instance

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

        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch (Objects.requireNonNull(tab.getText()).toString()) {
                    case "webViewTab":
                        fragment = webViewFragment; // Use the existing fragment instance
                        break;
                    case "Tab2":
                        fragment = new Tab2();
                        break;
                    case "Tab3":
                        fragment = new Tab3();
                        break;
                    default:
                        fragment = new Tab2();
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
