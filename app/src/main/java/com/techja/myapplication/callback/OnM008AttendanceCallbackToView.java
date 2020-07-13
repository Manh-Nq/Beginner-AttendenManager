package com.techja.myapplication.callback;

public interface OnM008AttendanceCallbackToView extends OnCallBackToView {
    void addAttendanceToView(String day, String time, String state);
}
