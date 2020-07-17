package com.techja.myapplication;

import android.app.Application;
import android.graphics.Typeface;

import com.techja.myapplication.utils.StorageCommon;

public class App extends Application {
    private static App instance;
    private static StorageCommon storage;
    private Typeface mBoldFont;
    private Typeface mRegularFont;
    private Typeface mItalicFont;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new StorageCommon();
        mRegularFont = Typeface.createFromAsset(getAssets(), "font/Roboto-Light.ttf");
        mItalicFont = Typeface.createFromAsset(getAssets(), "font/Roboto-Italic.ttf");
    }

    public static StorageCommon getStorage() {
        return storage;
    }
    public Typeface getRegularFont() {
        return mRegularFont;
    }

    public Typeface getBoldFont() {
        return mBoldFont;
    }




    public static App getInstance() {
        return instance;
    }
}
