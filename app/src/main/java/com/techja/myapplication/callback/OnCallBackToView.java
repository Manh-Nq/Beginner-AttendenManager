package com.techja.myapplication.callback;

import java.util.List;

public interface OnCallBackToView {
    default void showToast(String text) {
        // do nothing
    }

    default void updateUserInfoUI(List<String> string) {

    }
}
