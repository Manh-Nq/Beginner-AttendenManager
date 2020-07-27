package com.techja.myapplication.callback;

import com.techja.myapplication.model.TimeTableEntity;

import java.util.List;

public interface OnM005TimetableCallBackToView extends OnCallBackToView {

    void showProgressBar(boolean b);


//    void addTimetable(String day, String time, String detail, String teacher, String note);

    void loadDone(List<TimeTableEntity> listData);
}
