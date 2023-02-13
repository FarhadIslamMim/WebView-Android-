package com.onepointit.mim.rabaicmbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button btnReload;
    LinearLayout webLayout,reloadLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webViewId);
        btnReload = findViewById(R.id.btnReload);
        webLayout = findViewById(R.id.webViewMainLayout);
        reloadLayout = findViewById(R.id.noInternetLayout);

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });

        webView.loadUrl("http://rabaicmbd.com/");

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                webLayout.setVisibility(View.GONE);
                reloadLayout.setVisibility(View.VISIBLE);
                super.onReceivedSslError(view, handler, error);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }



    private void checkConnection(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null){

            if (networkInfo.isConnected()){
                webLayout.setVisibility(View.VISIBLE);
                reloadLayout.setVisibility(View.GONE);
                webView.reload();
            }
            else {
                webLayout.setVisibility(View.GONE);
                reloadLayout.setVisibility(View.VISIBLE);
            }
        }
        else {
            webLayout.setVisibility(View.GONE);
            reloadLayout.setVisibility(View.VISIBLE);
        }
    }
}