package com.techja.myapplication.callback;

import com.techja.myapplication.model.ItemDayEntity;

import java.util.List;

public interface OnM008AttendanceCallbackToView extends OnCallBackToView {
    void addAttendanceToView(List<ItemDayEntity> listData);

    void showProgressBar(boolean b);
}
