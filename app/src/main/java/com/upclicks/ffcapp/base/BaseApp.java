package com.upclicks.ffcapp.base;

import android.app.Application;

import com.upclicks.ffcapp.session.SessionHelper;
import javax.inject.Inject;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApp extends Application {
    @Inject
    SessionHelper sessionHelper;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
