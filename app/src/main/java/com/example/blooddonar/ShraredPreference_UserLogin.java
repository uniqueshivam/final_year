package com.example.blooddonar;

import android.content.Context;
import android.content.SharedPreferences;

public class ShraredPreference_UserLogin {
    private static ShraredPreference_UserLogin mInstance;
    private static final String shared_pref_name="mysharedpref";

    private static final String KEY_USER_EMAIL="email";
    private static final String KEY_USER_NAME="name";
    private static final String KEY_USER_MOBILE="mobile";
    private static final String KEY_USER_BLOOD_GROUP="blood_group";
    private static final String KEY_USER_POSTAL_ADDRESS="postal_address";
    private static final String KEY_USER_LATITUDE="latitude";
    private static final String KEY_USER_LONGITUDE="longitude";
    private static Context mCtx;


    public ShraredPreference_UserLogin(Context context) {
        mCtx = context;
    }

    public static synchronized ShraredPreference_UserLogin getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ShraredPreference_UserLogin(context);
        }
        return mInstance;
    }

    public boolean userlogin(String email, String name, String mobile, String blood_group, String postal_address,String latitude,String longitude)
    {
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL,email);
        editor.putString(KEY_USER_NAME,name);
        editor.putString(KEY_USER_MOBILE,mobile);
        editor.putString(KEY_USER_BLOOD_GROUP,blood_group);
        editor.putString(KEY_USER_POSTAL_ADDRESS,postal_address);
        editor.putString(KEY_USER_LATITUDE,latitude);
        editor.putString(KEY_USER_LONGITUDE,longitude);

        // editor.putString(KEY_AGENCY_RATING,agency_rating);
        editor.apply();
        return true;
    }
    public boolean isUserLogedin()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_MOBILE,null)!=null)
        {
            return true;

        }
        else
        {
            return false;
        }
    }

    public boolean logout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;

    }

    public String get_user_email()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL,null);

    }

    public String get_user_name()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME,null);

    }

    public String get_user_mobile()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_MOBILE,null);

    }

    public String get_user_blood_group()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_BLOOD_GROUP,null);

    }

    public String get_user_postal_address()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_POSTAL_ADDRESS,null);

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

}
