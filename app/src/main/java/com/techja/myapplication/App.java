package com.techja.myapplication;

import android.app.Application;
import android.graphics.Typeface;

import com.techja.myapplication.utils.StorageCommon;

public class App extends Application {
    private static App instance;
    private static StorageCommon storage;
    private Typeface mBoldFont;
    private Typeface mRegularFont;
    private Typeface mMediumFont;
    private Typeface mItalicFont;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new StorageCommon();
        mRegularFont = Typeface.createFromAsset(getAssets(), "font/fontRegular.ttf");
        mMediumFont = Typeface.createFromAsset(getAssets(), "font/fontMedium.ttf");
        mBoldFont = Typeface.createFromAsset(getAssets(), "font/fontBold.ttf");
        mItalicFont = Typeface.createFromAsset(getAssets(), "font/fontItalic.ttf");
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


    public Typeface getmMediumFont() {
        return mMediumFont;
    }

    public Typeface getmItalicFont() {
        return mItalicFont;
    }

    public static App getInstance() {
        return instance;
    }
}
