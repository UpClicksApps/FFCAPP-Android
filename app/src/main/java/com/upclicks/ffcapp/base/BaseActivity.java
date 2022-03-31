package com.upclicks.ffcapp.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.upclicks.ffcapp.R;
import com.upclicks.ffcapp.commons.Connectivity;
import com.upclicks.ffcapp.events.ErrorMessageEvent;
import com.upclicks.ffcapp.events.UnAuthorizedEvent;
import com.upclicks.ffcapp.session.SessionHelper;
import com.upclicks.ffcapp.ui.MainActivity;
import com.upclicks.ffcapp.ui.dialogs.ConfirmDialog;
import com.upclicks.ffcapp.ui.dialogs.MessageDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {
    @Inject public SessionHelper sessionHelper;
    @Inject public Connectivity connectivity;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        sessionHelper.configLanguage(this);
        setAdjustScreen();
    }


    @SuppressLint("PrivateResource")
    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    @SuppressLint("PrivateResource")
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    @SuppressLint("PrivateResource")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("ResourceType")
    public FragmentTransaction getTransaction() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.abc_fade_in,
                R.anim.abc_fade_out);
        return transaction;
    }


    public static ActivityOptionsCompat navWithTransition(Activity current, View view, String name){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(current,view, name);
        return options;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ErrorMessageEvent errorMessageEvent) {
        new MessageDialog(this,errorMessageEvent.getMsg()).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnAuthEvent(UnAuthorizedEvent unAuthorizedEvent) {
        sessionHelper.clearSession(BaseActivity.this);
      new ConfirmDialog(this, unAuthorizedEvent.getMsg()+" "+getString(R.string.doyouwantlogin), new ConfirmDialog.ConfirmClickActions() {
          @Override
          public void onConfirmed() {
              ActivityCompat.finishAffinity(BaseActivity.this);
           // startActivity(new Intent(BaseActivity.this, LoginActivity.class));
          }
      }).show();
    }

    protected void setAdjustScreen(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public boolean fingerprintEnabled()
    {
        FingerprintManager fingerprintManager = (FingerprintManager)getSystemService(FINGERPRINT_SERVICE);
        if (!fingerprintManager.isHardwareDetected())
        {
            // Device doesn't support fingerprint authentication
            new MessageDialog(this,"Device doesn't support fingerprint authentication")
                    .show();
            return false;
        }
        else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
        {
            return false;
        }
        else if (!fingerprintManager.hasEnrolledFingerprints())
        {
            // User hasn't enrolled any fingerprints to authenticate with
            new MessageDialog(this,"User hasn't enrolled any fingerprints to authenticate with")
                    .show();
            return false;
        }
        else
        {
            // Everything is ready for fingerprint authentication
            return true;
        }
    }


    //Restart App
    public static void restartApp(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
        ActivityCompat.finishAffinity((Activity) context);
    }

    //Logout
    public static void logout(Context context) {
       // context.startActivity(new Intent(context, LoginActivity.class));
        ActivityCompat.finishAffinity((Activity) context);
    }
}
