package com.techja.myapplication.model;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class TimeTableEntity implements Comparable<TimeTableEntity> {
    private static final String[] ARRDAY = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private String day, time, detail, teacher, note;

    public TimeTableEntity(String day, String time, String detail, String teacher, String note) {
        this.day = day;
        this.time = time;
        this.detail = detail;
        this.teacher = teacher;
        this.note = note;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof TimeTableEntity) {
            TimeTableEntity item = (TimeTableEntity) obj;
            return item.day.equals(day) && item.detail.equals(detail)
                    && item.note.equals(note) && item.teacher.equals(teacher)
                    && item.time.equals(time) && item.note.equals(note);
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(TimeTableEntity o) {
        if (o == null || day == null) return 0;
        if (day.equals(o.getDay())) {
            if (time.length() != o.getTime().length()) return time.length() - o.getTime().length();
            return time.compareTo(o.getTime());
        }
        int index1 = Arrays.asList(ARRDAY).indexOf(day);
        int index2 = Arrays.asList(ARRDAY).indexOf(o.getDay());

        return index1 - index2;
    }

}
