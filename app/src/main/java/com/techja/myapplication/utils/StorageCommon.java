package com.techja.myapplication.utils;


import com.techja.myapplication.model.ClassEntity;
import com.techja.myapplication.model.TimeTableEntity;

import java.util.ArrayList;

public class StorageCommon {
    private String currentUId;
    private String currentFrgTag;
    private String classCode;
    private long numberCallPhone;
    private ArrayList<ClassEntity> listClass;


    public StorageCommon() {
        listClass = new ArrayList<>();

    }



    public String getCurrentUId() {
        return currentUId;
    }

    public ArrayList<ClassEntity> getListClass() {
        return listClass;
    }

    public void addClass(String className, String classCode) {
        ClassEntity entity = new ClassEntity(className, classCode);
        if (listClass.contains(entity)) {
            return;
        }
        listClass.add(entity);
    }

    public void setCurrentUId(String currentUId) {
        this.currentUId = currentUId;
    }

    public long getNumberCallPhone() {
        return numberCallPhone;
    }

    public void setNumberCallPhone(long numberCallPhone) {
        this.numberCallPhone = numberCallPhone;
    }

    public String getCurrentFrgTag() {
        return currentFrgTag;
    }

    public void setCurrentFrgTag(String currentFrgTag) {
        this.currentFrgTag = currentFrgTag;
    }

    public void setClassCode(String s) {
        this.classCode = s;
    }

    public String getClassCode() {
        return classCode;
    }


}
