package com.upclicks.ffcapp.fcm;

import com.google.firebase.iid.FirebaseInstanceIdService;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
//    @Inject public SessionHelper sessionHelper;
//    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
//
//    @Override
//    public void onTokenRefresh() {
//        super.onTokenRefresh();
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        // Saving reg id to shared preferences
//        storeRegIdInPref(refreshedToken);
//    }
//
//    private void storeRegIdInPref(String token) {
//        Log.e(TAG, "sendRegistrationToServer: " + token);
//        sessionHelper.savePushNotificationToken(token);
//    }
}