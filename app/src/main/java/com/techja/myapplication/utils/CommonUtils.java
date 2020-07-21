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
    private static final Object[][] PHONE_NUMBER = new Object[][]{
            new Object[]{"090", 10}, new Object[]{"093", 10}, new Object[]{"070", 10},
            new Object[]{"071", 10}, new Object[]{"072", 10}, new Object[]{"076", 10},
            new Object[]{"078", 10}, new Object[]{"089", 10},
            new Object[]{"096", 10}, new Object[]{"097", 10}, new Object[]{"098", 10},
            new Object[]{"032", 10}, new Object[]{"033", 10}, new Object[]{"034", 10},
            new Object[]{"035", 10}, new Object[]{"036", 10}, new Object[]{"037", 10},
            new Object[]{"038", 10}, new Object[]{"039", 10}, new Object[]{"086", 10},
            new Object[]{"091", 10}, new Object[]{"094", 10}, new Object[]{"088", 10},
            new Object[]{"083", 10}, new Object[]{"084", 10}, new Object[]{"085", 10},
            new Object[]{"081", 10}, new Object[]{"082", 10},
            new Object[]{"092", 10}, new Object[]{"052", 10}, new Object[]{"088", 10},
            new Object[]{"058", 10}, new Object[]{"056", 10},
            new Object[]{"099", 10}, new Object[]{"059", 10}, new Object[]{"0296", 12},
            new Object[]{"0254", 12}, new Object[]{"0209", 12}, new Object[]{"0204", 12},
            new Object[]{"0291", 12}, new Object[]{"0222", 12}, new Object[]{"0275", 12},
            new Object[]{"0256", 12}, new Object[]{"0274", 12}, new Object[]{"0271", 12},
            new Object[]{"0252", 12}, new Object[]{"0290", 12}, new Object[]{"0292", 12},
            new Object[]{"0206", 12}, new Object[]{"0236", 12}, new Object[]{"0262", 12},
            new Object[]{"0261", 12}, new Object[]{"0215", 12}, new Object[]{"0251", 12},
            new Object[]{"0277", 12}, new Object[]{"0269", 12}, new Object[]{"0226", 12},
            new Object[]{"024", 11}, new Object[]{"0239", 12}, new Object[]{"0220", 12},
            new Object[]{"0225", 12}, new Object[]{"0293", 12}, new Object[]{"028", 11},
            new Object[]{"0221", 12}, new Object[]{"0258", 12}, new Object[]{"0297", 12},
            new Object[]{"0260", 12}, new Object[]{"0213", 12}, new Object[]{"0263", 12},
            new Object[]{"0205", 12}, new Object[]{"0214", 12}, new Object[]{"0272", 12},
            new Object[]{"0228", 12}, new Object[]{"0238", 12}, new Object[]{"0259", 12},
            new Object[]{"0229", 12}, new Object[]{"0257", 12}, new Object[]{"0232", 12},
            new Object[]{"0235", 12}, new Object[]{"0255", 12}, new Object[]{"0203", 12},
            new Object[]{"0233", 12}, new Object[]{"0299", 12}, new Object[]{"0212", 12},
            new Object[]{"0276", 12}, new Object[]{"0227", 12}, new Object[]{"0208", 12},
            new Object[]{"0237", 12}, new Object[]{"0234", 12}, new Object[]{"0273", 12},
            new Object[]{"0294", 12}, new Object[]{"0207", 12},
            new Object[]{"0270", 12}, new Object[]{"0216", 12},
    };


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
    public static boolean isPhone(String phone) {
        for (Object[] item : PHONE_NUMBER) {
            String prefix = (String) item[0];
            int length = (int) item[1];
            if (phone.startsWith(prefix) && phone.length() == length) {
                return true;
            }
        }
        return false;
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
