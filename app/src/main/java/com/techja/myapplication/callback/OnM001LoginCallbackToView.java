package com.techja.myapplication.callback;

import java.util.List;

public interface OnM001LoginCallbackToView extends OnCallBackToView {
    void showMenuFragment();

    void showProgressbar(boolean b);

    void getCodeAdmin(List<String> listData);
}
