package com.upclicks.ffcapp.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.upclicks.ffcapp.models.response.Profile;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class SessionHelper {

    private static final String SHARED_PREFERENCES_FILE = "com.upclicks.farw.sharedpreferences";
    private static final String NOTIFICATIONS_SHAREDPREFS="notificationsSharedPres";
    private static final String USER_SESSION = SHARED_PREFERENCES_FILE + ".usersession";
    private static final String LANGUAGE = ".language";
    private static final String PUSH_TOKEN = SHARED_PREFERENCES_FILE + ".pushtoken";
    private static final String TRIP_REQUEST = SHARED_PREFERENCES_FILE + ".trip_request";
    private static final String ARABIC = "عربى";
    private static final String ENGLISH = "English";

    private static final String RUNNING_TRIP_ACTIVITY_STATUS="runningtripactivitystatus";

    private static final String AR = "ar";
    private static final String EN = "en";
    SharedPreferences sharedPref;
    private static UserSession userSession;
    Context context;

    @Inject public SessionHelper(@ApplicationContext Context context) {
        this.context=context;
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    //Save User Session
    public void setUserSession(UserSession fUserSession) {
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson =new Gson();
        editor.putString(USER_SESSION,gson.toJson(fUserSession));
        editor.apply();
        userSession = fUserSession;
    }

    //Get User Session
    @NonNull
    private UserSession getUserSession() {
        if (userSession == null) {
            Gson gson =new Gson();
            userSession = gson.fromJson(sharedPref.getString(USER_SESSION, null),UserSession.class) ;
        }
        return userSession;
    }

    //Get User Profile
    public Profile getUserInfo() {
        return getUserSession().getProfile();
    }

    //Get user token
    @Nullable
    public String userToken() {
        return getUserSession().getAccessToken();
    }

    //Check if user IsLogin
    public boolean isLogin() {
        return getUserSession() != null && !getUserSession().getAccessToken().isEmpty();
    }

    //Save Push Token FCM
    public void savePushNotificationToken(String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PUSH_TOKEN, token);
        editor.apply();
    }

    //Get push token FCM
    public String getPushNotificationToken() {
        return sharedPref.getString(PUSH_TOKEN, "");
    }


    //Logout
    public void logout(SessionCallBack sessionCallBack) {
        // Clear saved data
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
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
        editor.clear();
        editor.apply();
        // Reset session
        userSession = null;
    }







    //Set User Lang
    public void setUserLanguageSession(String Language, OnSessionUpdate onSessionUpdate) {
        if (!getUserLanguage(context).equals(Language)) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(LANGUAGE, Language);
            editor.apply();
            setLocale(getUserLanguageCode(), context);
            onSessionUpdate.refreshActivity();
        }
    }
    //Get User language
    public  String getUserLanguage(Context context) {
        String lang = sharedPref.getString(LANGUAGE, null);
        return lang != null ? lang : ENGLISH;
    }
    //Check if lang IsSelected
    public boolean isLangSelected(Context context){
        String lang = sharedPref.getString(LANGUAGE, null);
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