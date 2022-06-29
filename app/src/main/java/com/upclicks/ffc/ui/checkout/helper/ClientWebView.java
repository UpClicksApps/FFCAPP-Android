package com.upclicks.ffc.ui.checkout.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ClientWebView  extends WebViewClient {
    private Context context;
    private String callbackUrl;
    private int sec;
    public ClientWebView(Context context, int sec, String callbackUrl) {
        this.context = context;
        this.callbackUrl = callbackUrl;
        this.sec=sec;
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(url.contains(callbackUrl)){
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
