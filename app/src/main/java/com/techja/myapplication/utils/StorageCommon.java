package com.techja.myapplication.utils;


import com.techja.myapplication.model.ClassEntity;
import com.techja.myapplication.model.StudentEntity;

import java.util.ArrayList;

public class StorageCommon {
    private String currentUId;
    private String currentFrgTag;
    private String classCode;
    private long numberCallPhone;
    private ClassEntity classEntity;
    private ArrayList<ClassEntity> listClass;
    private ArrayList<StudentEntity> listStudent;
    private StudentEntity studentEntity;


    public StorageCommon() {
        listClass = new ArrayList<>();
        listStudent = new ArrayList<>();

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

    public void addStudent(String name, String className, String email) {
        StudentEntity entity = new StudentEntity(name, className, email);
        if (listStudent.contains(entity)) {
            return;
        }
        listStudent.add(entity);
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

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }
}
