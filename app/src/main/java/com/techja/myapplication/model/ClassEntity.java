package com.techja.myapplication.model;

import androidx.annotation.Nullable;

public class ClassEntity {
    private String className, classCode;

    public ClassEntity(String className, String classCode) {
        this.className = className;
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof ClassEntity){
            ClassEntity item = (ClassEntity) obj;
            return item.classCode.equals(classCode) && item.className.equals(className);
        }
        return super.equals(obj);
    }
}
