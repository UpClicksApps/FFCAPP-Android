package com.upclicks.ffc.ui.checkout.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ClientWebView  extends WebViewClient {
    private Context context;
    private String url;
    private int sec;
    public ClientWebView(Context context, int sec, String url) {
        this.context = context;
        this.url = url;
        this.sec=sec;
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(!this.url.equals(url)){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((Activity)context).setResult(Activity.RESULT_OK);
                    ((Activity)context).finish();
                }
            }, sec*1000);   //5 sec

        }
    }
}
