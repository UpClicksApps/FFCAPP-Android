package com.upclicks.ffc.ui.notification;

import android.content.Context;

import com.upclicks.ffc.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetTimeAgo {
    /*
     *----THIS CLASS IS FOR GETTING LAST SEEN TIME IN CHATS ----
     * ---IT CONVERTS TIME_STAMP INTO TIME_AGO
     */

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String getTimeAgo(long time, Context ctx) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }
        //long now = getCurrentTime(ctx);
        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return ctx.getResources().getString(R.string.just_now);
        }
        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.just_now);
        } else if (diff < 2 * MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.a_min_ago);
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS +" "+ctx.getResources().getString(R.string.a_min_ago);
        } else if (diff < 90 * MINUTE_MILLIS) {
            return ctx.getResources().getString(R.string.hour_ago);
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS +" "+ctx.getResources().getString(R.string.a_min_ago);
        } else if (diff < 48 * HOUR_MILLIS) {
            return ctx.getResources().getString(R.string.yesterday);
        } else {
            return "";
        }
    }

    public static  long getTimeInMillies(String datef){
        long time =0 ;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;

        try{
            //formatting the dateString to convert it into a Date
            date = dateFormat.parse(datef);
            Calendar calendar = Calendar.getInstance();
            //Setting the Calendar date and time to the given date and time
            calendar.setTime(date);
            time =  calendar.getTimeInMillis();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return time;
    }
}