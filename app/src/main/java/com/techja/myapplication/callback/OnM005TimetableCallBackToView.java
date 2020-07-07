package com.techja.myapplication.callback;

public interface OnM005TimetableCallBackToView extends OnCallBackToView {
    void addTimetable(String day, String time, String detail, String teacher, String note);
}
