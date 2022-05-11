package com.upclicks.ffc.ui.general.component.numberPicker.Listener;

import android.util.Log;

import com.upclicks.ffc.ui.general.component.numberPicker.Interface.LimitExceededListener;


/**
 * Created by travijuu on 26/05/16.
 */
public class DefaultLimitExceededListener implements LimitExceededListener {

    public void limitExceeded(int limit, int exceededValue) {

        String message = String.format("NumberPicker cannot set to %d because the limit is %d.", exceededValue, limit);
        Log.v(this.getClass().getSimpleName(), message);
    }
}
