package com.techja.myapplication.callback;

import com.techja.myapplication.model.ClassEntity;

import java.util.List;

public interface OnM004ClassCallbackToView extends OnCallBackToView {
    void updateData(List<ClassEntity> listData);

    void showProgressBar(boolean b);
}
