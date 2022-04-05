package com.upclicks.ffc.data;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class BaseURLConfigHelper {
    private static final String BASE_URL_KEY = "base_url";
    private static DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private static DatabaseReference mBaseUrlRef = mRootRef.child(BASE_URL_KEY);
    Context context;

    @Inject
    public BaseURLConfigHelper(@ApplicationContext Context context) {
        this.context = context;
    }

    public void configBaseUrl(BaseUrlInterface baseUrlInterface) {
//        retrieve the value if changed
        mBaseUrlRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (!TextUtils.isEmpty(dataSnapshot.getValue(String.class))) {
                        baseUrlInterface.onBaseUrlChanged(dataSnapshot.getValue(String.class));
                        Log.v("url", dataSnapshot.getValue(String.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.toException().printStackTrace();

            }
        });
    }

    public interface BaseUrlInterface {
        void onBaseUrlChanged(String baseUrl);
    }
}
