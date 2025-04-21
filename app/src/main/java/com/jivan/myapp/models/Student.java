package com.jivan.myapp.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("roll_no")
    private int rollNo;
    private String firstName;
    private String lastName;
    private String address;
    private Course course;

    public Student(int rollNo, String firstName, String lastName, String address, Course course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNo = rollNo;
        this.address = address;
        this.course = course;
    }

    @NonNull
    public String toString() {
        return firstName + " " + lastName + ", Roll No.: " +
                rollNo + "\n From: " + address + " of " +
                course.getName() + " : " +
                course.getDesc() + " " + course.getDuration() + " years course";
    }
}


