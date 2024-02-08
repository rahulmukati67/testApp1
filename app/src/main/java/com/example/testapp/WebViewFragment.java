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
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.airbnb.lottie.LottieAnimationView;

public class WebViewFragment extends Fragment implements ConnectivityChangeListener , SwipeRefreshLayout.OnRefreshListener {
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String currentUrl;
    private LottieAnimationView animationView;
    private boolean isConnected;
    Bundle webViewState;
    SwipeRefreshLayout swipeRefreshLayout;
    private TextView NointernetTxtView;
    private boolean isUrlLoaded = false;

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
                    loadUrl();
            }
            onConnectivityChanged(isConnected);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = view.findViewById(R.id.webViewElement);
        mProgressBar = view.findViewById(R.id.progressBar);
        animationView = view.findViewById(R.id.animationView);
        NointernetTxtView = view.findViewById(R.id.NointernetTxtView);
        animationView.setVisibility(View.VISIBLE);
        NointernetTxtView.setVisibility(View.VISIBLE);
        mWebView.setVisibility(View.GONE);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getContext().registerReceiver(connectivityReceiver, filter);

        mWebView.getSettings().setJavaScriptEnabled(true);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

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
                isUrlLoaded = true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                currentUrl = request.getUrl().toString();
                Log.d("WebViewFragment", "currentUrl" + currentUrl);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    getParentFragmentManager().popBackStack();
                    Intent intent = new Intent(requireContext(), startActivity.class);
                    startActivity(intent);
                }
            }
        });
        if (!isUrlLoaded) {
            loadUrl();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
         webViewState = new Bundle();
        mWebView.saveState(webViewState);
        outState.putBundle("WebViewState", webViewState);
    }
    private void loadUrl() {
        if (currentUrl != null && !currentUrl.isEmpty()) {
            Log.d("WebViewFragment", "Loading URL: " + currentUrl);
            mWebView.loadUrl(currentUrl);
        } else {
            Log.d("WebViewFragment", "Default URL loaded as currentUrl is empty");
            mWebView.loadUrl("https://www.google.com/");
        }
    }

    @Override
    public void onConnectivityChanged(boolean isConnected) {
        if (isConnected) {
            animationView.setVisibility(View.GONE);
            NointernetTxtView.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        mWebView.reload();
        swipeRefreshLayout.setRefreshing(false);
        Log.d("Refresh" , "Refreashing");
    }
}
