package com.androiddev.sharvani.wikiimagesearch.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androiddev.sharvani.wikiimagesearch.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import static com.androiddev.sharvani.wikiimagesearch.R.id.webview;

@EActivity(R.layout.activity_web_view)
public class WebViewActivity extends AppCompatActivity {

    @ViewById(webview)
    WebView webView;

    @Extra
    String url;

    @AfterViews
    public void init() {
        webView.setWebViewClient(new CustomWebViewClient());
        webView.loadUrl(url);
    }

    class CustomWebViewClient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }


    }
}
