package com.upclicks.ffc.session;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.upclicks.ffc.ui.authentication.model.Session;
import com.upclicks.ffc.ui.authentication.model.response.VerifySession;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class SessionHelper {
    private static final String SHARED_PREFERENCES_FILE = "com.upclicks.farw.sharedpreferences";
    private static final String USER_SESSION = SHARED_PREFERENCES_FILE + ".usersession";
    private static final String USER_PROFILE = SHARED_PREFERENCES_FILE + ".profile";
    private static final String LANGUAGE = ".language";
    private static final String PUSH_TOKEN = SHARED_PREFERENCES_FILE + ".pushtoken";
    public static final String ARABIC = "عربى";
    public static final String ENGLISH = "English";
    private static final String DARK_MODE = "dark_mode";
    private static final String DEVICE_ID="device_id";
    private static final String CART_COUNT="cart_count";
    private static final String AR = "ar";
    private static final String EN = "en";
    private static final String INTRO_LANGUAGE="intro_language";
    SharedPreferences sharedPref;
    private static Session userSession;
    private static VerifySession.Profile profile;
    Context context;

    @Inject
    public SessionHelper(@ApplicationContext Context context) {
        this.context=context;
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    //Save User Session
    public void setUserSession(Session fUserSession) {
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson =new Gson();
        editor.putString(USER_SESSION,gson.toJson(fUserSession));
        editor.apply();
        userSession = fUserSession;
    }

    //Get App Language
    public  String getAppLanguage() {
        String code = getLanguagesCodes().get(getLanguageIndex(context));
        if(code.equals(AR))
            return "ar-EG"  ;

        return "en-US";

    }

    //Save User Profile
    public void setUserProfile(VerifySession.Profile userProfile) {
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson =new Gson();
        editor.putString(USER_PROFILE,gson.toJson(userProfile));
        editor.apply();
        profile = userProfile;
    }

    //Get User Profile
    @NonNull
    public VerifySession.Profile getUserProfile() {
        if (profile == null) {
            Gson gson =new Gson();
            profile = gson.fromJson(sharedPref.getString(USER_PROFILE, null),VerifySession.Profile.class) ;
        }
        return profile;
    }



    //Save device id
    public void saveDeviceId(String deviceId) {
        SharedPreferences.Editor editor =  this.context.getSharedPreferences(DEVICE_ID, Context.MODE_PRIVATE).edit();
        editor.putString(DEVICE_ID, deviceId);
        editor.apply();
    }

    //Get device id
    public String getDeviceId() {
        SharedPreferences preferences =  this.context.getSharedPreferences(DEVICE_ID, Context.MODE_PRIVATE);
        return preferences.getString(DEVICE_ID, "");
    }



    //Save cart count
    public void saveCartCount(Integer count) {
        SharedPreferences.Editor editor =  this.context.getSharedPreferences(CART_COUNT, Context.MODE_PRIVATE).edit();
        VerifySession.Profile profile = getUserProfile();
        profile.setCurrentCartProductsCount(count);
        setUserProfile(profile);
        editor.putInt(CART_COUNT, count);
        editor.apply();
    }

    //Get cart count
    public Integer getCartCount() {
        SharedPreferences preferences =  this.context.getSharedPreferences(CART_COUNT, Context.MODE_PRIVATE);
        return preferences.getInt(CART_COUNT, 0);
    }



    //Save Dark Mode
    public void saveDarkMode(int mode) {
        SharedPreferences.Editor editor =  this.context.getSharedPreferences(DARK_MODE, Context.MODE_PRIVATE).edit();
        editor.putInt(DARK_MODE, mode);
        editor.apply();
    }

    //Get Dark Mode
    public int getDarkMode() {
        SharedPreferences preferences =  this.context.getSharedPreferences(DARK_MODE, Context.MODE_PRIVATE);
        return preferences.getInt(DARK_MODE, -1);
    }

    //Save IntroLanguage status
    public void chooseIntroLanguage(boolean isChoose) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(INTRO_LANGUAGE, isChoose);
        editor.apply();
    }

    //Get IntroLanguage
    public Boolean isIntroLanguageChoose() {
        return sharedPref.getBoolean(INTRO_LANGUAGE, false);
    }



    //Get User Session
    @NonNull
    public Session getUserSession() {
        if (userSession == null) {
            Gson gson =new Gson();
            userSession = gson.fromJson(sharedPref.getString(USER_SESSION, null),Session.class) ;
        }
        return userSession;
    }

    //Get user token
    @Nullable
    public String userToken() {
        if (getUserSession() == null)
            return "";
        else
            return getUserSession().getAccessToken();
    }


    //Check if user IsLogin
    public boolean isLogin() {
        return getUserSession() != null && !getUserSession().getAccessToken().isEmpty();
    }

    //Save Push Token FCM
    public void savePushNotificationToken(String token) {
        SharedPreferences.Editor editor =  this.context.getSharedPreferences(PUSH_TOKEN, Context.MODE_PRIVATE).edit();
        editor.putString(PUSH_TOKEN, token);
        editor.apply();
    }


    //Get push token FCM
    public String getPushNotificationToken() {
        SharedPreferences preferences =  this.context.getSharedPreferences(PUSH_TOKEN, Context.MODE_PRIVATE);
        return preferences.getString(PUSH_TOKEN, "");
    }


    //Logout
    public void logout(SessionCallBack sessionCallBack) {
        // Clear saved data
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(USER_SESSION);
        editor.remove(USER_PROFILE);
        editor.apply();
        // Reset session
        userSession = null;
        // Notify
        sessionCallBack.setOnLogout();
    }

    //clear session
    public void clearSession(Context context) {
        // Clear saved data
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(USER_SESSION);
        editor.apply();
        // Reset session
        userSession = null;
    }

    //Set User Lang
    public void setUserLanguageSession(String Language, OnSessionUpdate onSessionUpdate) {
        if (!getUserLanguage(context).equals(Language)) {
            SharedPreferences.Editor editor =  this.context.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE).edit();
            editor.putString(LANGUAGE, Language);
            editor.apply();
            setLocale(getUserLanguageCode(), context);
            onSessionUpdate.refreshActivity();
        }
    }
    //Get User language
    public  String getUserLanguage(Context context) {
        String lang = this.context.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE).getString(LANGUAGE, null);
        return lang != null ? lang : ENGLISH;
    }
    //Check if lang IsSelected
    public boolean isLangSelected(Context context){
        String lang = this.context.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE).getString(LANGUAGE, null);
        return lang != null;
    }
    public  Context configLanguage(Context context) {
        return setLocale(getUserLanguageCode(), context);
    }
    public  String getUserLanguageCode() {
        return getLanguagesCodes().get(getLanguageIndex(context));
    }

    public boolean isEnglish(Context context) {
        return getUserLanguage(context).equals(ENGLISH);
    }
    public  boolean isArabic(Context context) {
        return getUserLanguage(context).equals(ARABIC);
    }
    public  void setLanguageEnglish( OnSessionUpdate onSessionUpdate) {
        setUserLanguageSession(ENGLISH, onSessionUpdate);
    }
    public  void setLanguageArabic(OnSessionUpdate onSessionUpdate) {
        setUserLanguageSession(ARABIC, onSessionUpdate);
    }






    public static List<String> getLanguages() {
        return Arrays.asList(ARABIC, ENGLISH);
    }
    private static List<String> getLanguagesCodes() {
        return Arrays.asList(AR,EN);
    }
    public int getLanguageIndex(Context context) {
        int index = getLanguages().indexOf(getUserLanguage(context));
        return index == -1 ? 1 : index;
    }
    private Context setLocale(String lang, Context context) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        //configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }


    public interface SessionCallBack {
        void setOnLogout();
    }
    public interface OnSessionUpdate {
        void refreshActivity();
    }
}
