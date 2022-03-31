package com.upclicks.ffcapp.data;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BaseURLConfigHelper {
    private static final String BASE_URL_KEY = "base_url";
    private static DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private static DatabaseReference mBaseUrlRef = mRootRef.child(BASE_URL_KEY);


    @Inject public BaseURLConfigHelper() {
    }

    public void configBaseUrl(final Context context, BaseUrlInterface baseUrlInterface) {
        //retrieve the value if changed
        mBaseUrlRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    baseUrlInterface.onBaseUrlChanged(dataSnapshot.getValue(String.class));
                    Log.v("url", dataSnapshot.getValue(String.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface BaseUrlInterface{
        void onBaseUrlChanged(String baseUrl);
    }
}
