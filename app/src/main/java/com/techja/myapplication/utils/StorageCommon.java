package com.techja.myapplication.utils;


import com.techja.myapplication.model.ClassEntity;
import com.techja.myapplication.model.CompanyEntity;
import com.techja.myapplication.model.StudentEntity;
import com.techja.myapplication.model.TimeTableEntity;

import java.util.ArrayList;

public class StorageCommon {
    private String currentUId;
    private String currentFrgTag;
    private String classCode;
    private String numberCallPhone;
    private ClassEntity classEntity;
    private ArrayList<ClassEntity> listClass;
    private ArrayList<StudentEntity> listStudent;
    private ArrayList<TimeTableEntity> listTable;
    private StudentEntity studentEntity;
    private CompanyEntity companyEntity;
    private TimeTableEntity timeTableEntity;


    public StorageCommon() {
        listClass = new ArrayList<>();
        listStudent = new ArrayList<>();
        listTable = new ArrayList<>();

    }

    public TimeTableEntity getTimeTableEntity() {
        return timeTableEntity;
    }

    public void setTimeTableEntity(TimeTableEntity timeTableEntity) {
        this.timeTableEntity = timeTableEntity;
    }

    public ArrayList<TimeTableEntity> getListTable() {

        return listTable;
    }

    public void addListTable(String day, String time, String detail, String teacher, String note) {

        TimeTableEntity entity = new TimeTableEntity(day, time, detail, teacher, note);
        if (listTable.contains(entity)) {
            return;
        }
        listTable.add(entity);
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
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

    public ArrayList<StudentEntity> getListStudent() {
        return listStudent;
    }

    public void addStudent(String name, String className, String email, String phone) {
        StudentEntity entity = new StudentEntity(name, className, email, phone);
        if (listStudent.contains(entity)) {
            return;
        }
        listStudent.add(entity);
    }

    public void setCurrentUId(String currentUId) {
        this.currentUId = currentUId;
    }

    public String getNumberCallPhone() {
        return numberCallPhone;
    }

    public void setNumberCallPhone(String numberCallPhone) {
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

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }
}
