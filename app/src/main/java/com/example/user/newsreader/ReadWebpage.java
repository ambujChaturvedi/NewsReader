package com.example.user.newsreader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ReadWebpage extends AppCompatActivity {
    private static final String TAG = "ReadWebpage";
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_webpage);

        //Toast.makeText(this,getIntent().getStringExtra("weburl"),Toast.LENGTH_SHORT).show();
        String WEBURL=getIntent().getStringExtra("weburl");

        webView=(WebView) findViewById(R.id.webview);
        progressBar=(ProgressBar) findViewById(R.id.progres_bar);

        setUpWebView(WEBURL);
    }

    private void setUpWebView(String weburl) {

        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG, "onPageStarted: ");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "onPageFinished: ");
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });
        webView.loadUrl(weburl);
    }
}
