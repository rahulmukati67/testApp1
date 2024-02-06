package com.example.testapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

public class WebViewFragment extends Fragment implements ConnectivityChangeListener {
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String currentUrl;
    private LottieAnimationView animationView;
    private boolean isConnected;
    private TextView NointernetTxtView;

    private BroadcastReceiver connectivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            if (isConnected) {
                animationView.setVisibility(View.GONE);
                NointernetTxtView.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }
            onConnectivityChanged(isConnected);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web_view, container, false);
        mWebView = v.findViewById(R.id.webViewElement);
        mProgressBar = v.findViewById(R.id.progressBar);
        animationView = v.findViewById(R.id.animationView);
        NointernetTxtView=v.findViewById(R.id.NointernetTxtView);
        animationView.setVisibility(View.VISIBLE);
        NointernetTxtView.setVisibility(View.VISIBLE);
        mWebView.setVisibility(View.GONE);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getContext().registerReceiver(connectivityReceiver, filter);

        mWebView.getSettings().setJavaScriptEnabled(true);

        if (savedInstanceState != null) {
            currentUrl = savedInstanceState.getString("currentUrl");
            loadUrl();
        }
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (isConnected) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                if (!isConnected) {
                    animationView.setVisibility(View.VISIBLE);
                    NointernetTxtView.setVisibility(View.VISIBLE);
                    mWebView.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });
        loadUrl();

        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentUrl", mWebView.getUrl());
        Log.d("WebViewFragment", "URL Instace Save + "+ mWebView.getUrl());
    }

    private void loadUrl() {
        if (currentUrl != null && !currentUrl.isEmpty()) {
            Log.d("WebViewFragment", "Loading URL: " + currentUrl);
            mWebView.loadUrl(currentUrl);
        } else {
            Log.d("WebViewFragment", "Default URL loaded as currentUrl is empty");
            mWebView.loadUrl("https://www.google.com");
        }
    }

    @Override
    public void onConnectivityChanged(boolean isConnected) {
        if (isConnected) {
            animationView.setVisibility(View.GONE);
            NointernetTxtView.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
//            loadUrl();
        }
    }
}
