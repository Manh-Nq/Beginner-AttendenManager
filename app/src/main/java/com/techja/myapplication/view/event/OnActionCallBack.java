package com.techja.myapplication.view.event;

public interface OnActionCallBack {
    default void callBack(String key, Object data) {
        // for use later
    }

    void showFragment(String tag);


}
