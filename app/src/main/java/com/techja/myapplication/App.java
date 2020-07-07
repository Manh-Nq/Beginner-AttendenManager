package com.techja.myapplication;

import android.app.Application;

import com.techja.myapplication.utils.StorageCommon;

public class App extends Application {
    private static App instance;
    private static StorageCommon storage;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new StorageCommon();
    }

    public static StorageCommon getStorage() {
        return storage;
    }


    public static App getInstance() {
        return instance;
    }
}
