package com.techja.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;

import com.techja.myapplication.App;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtils {

    public static final String DATE_DD_MMM_YYY = "dd/MM/yyy";
    public static final String DATE_DD_MMM_YYY_FULL = "dd/MM/yyy HH:mm:ss";
    public static final String DATE_E_DD_MMM_YYY = "dd/MM/yyy ',' HH:mm:ss ',' E";
    private static final String SAVE_USER_NAME_PASS = "SAVE_USER_NAME_PASS";
    private static final String KEY_EMAIL = "KEY_USER_NAME";
    private static final String KEY_PASSWORD = "KEY_PASSWORD";
    private static final String KEY_ADMIN = "KEY_ADMIN";


    private static CommonUtils instance;
    private SharedPreferences.Editor editor;


    private CommonUtils() {
        // for singleton
    }

    public static CommonUtils getInstance() {
        if (instance == null) {
            instance = new CommonUtils();
        }
        return instance;
    }

    public String getDateNow(String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.US);
        return df.format(new Date());
    }

    public void saveAccount(String email, String password,String codeAdmin) {
        SharedPreferences sharedPref = App.getInstance().getSharedPreferences(SAVE_USER_NAME_PASS, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_ADMIN, codeAdmin);

        editor.apply();
        Log.d("TAG", "saveAccount: " + email + password);
    }

    public String[] getAccount() {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(SAVE_USER_NAME_PASS, Context.MODE_PRIVATE);
        String[] arrData = new String[]{sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_ADMIN, null)};
        return arrData;
    }


    public void turnOnGPSIfNeeds(Context context) {
        final LocationManager manager = (LocationManager) App.getInstance().getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
    }


}
