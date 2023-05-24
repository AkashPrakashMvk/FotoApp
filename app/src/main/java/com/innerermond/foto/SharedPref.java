package com.innerermond.foto;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{
    private static SharedPreferences mSharedPref;
    public static final String POINTS = "POINTS";
    public static final String POINTS1 = "POINTS1";
    public static final String POINTS2 = "POINTS2";
    public static final String POINTS3 = "POINTS3";
    public static final String ADSANDC = "ADSANDC";

    public static final String BGREMOVE = "BGREMOVE";
    public static final String DSAGAIN = "DSAGAIN";
    public static final String DSCAMERA = "DSCAMERA";
    public static final String FTP = "FTP";
    public static final String ISGAME = "FTP";
    public static final String PRESSSPLUS = "PRESSSPLUS";
    public static final String SKIP = "SKIP";
    public static final String SKIP2 = "SKIP2";









    public static final String FTL = "FTL";



    private SharedPref()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }
}