package com.example.blooddonar;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference_for_currentLocation {

    private static SharedPreference_for_currentLocation mInstance;
    private static final String shared_pref_name="mysharedpref_location";

    private static final String KEY_USER_LATITUDE="latitude";
    private static final String KEY_USER_LONGITUDE="longitude";
    private static final String KEY_USER_POSTAL_ADDRESS="address";
    private static Context mCtx;

    public SharedPreference_for_currentLocation(Context context) {
        mCtx = context;
    }



    public static synchronized SharedPreference_for_currentLocation getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreference_for_currentLocation(context);
        }
        return mInstance;
    }


    public boolean Logged_user_get_location(String latitude,String longitude,String postal_address)
    {
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_USER_LATITUDE,latitude);
        editor.putString(KEY_USER_LONGITUDE,longitude);
        editor.putString(KEY_USER_POSTAL_ADDRESS,postal_address);

        // editor.putString(KEY_AGENCY_RATING,agency_rating);
        editor.apply();
        return true;
    }


    public String get_user_latitude()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_LATITUDE,null);

    }


    public String get_user_longitude()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_LONGITUDE,null);

    }

    public String get_user_postal_address()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_POSTAL_ADDRESS,null);

    }

}
