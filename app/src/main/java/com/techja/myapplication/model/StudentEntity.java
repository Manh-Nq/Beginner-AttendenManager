package com.techja.myapplication.model;

import androidx.annotation.Nullable;

public class StudentEntity {
    private String name, className;
    private String email;
    private String phone;

    public StudentEntity(String name, String className, String email,String phone) {
        this.name = name;
        this.className = className;
        this.email = email;
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof StudentEntity) {
            StudentEntity item = (StudentEntity) obj;
            return item.name.equals(name) && item.className.equals(className)
                    && item.email.equals(email) && item.phone.equals(phone);
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
