package com.jivan.myapp.models;

public class Course{
    private String name;
    private String desc;
    private int duration;

    public Course(String name, String desc, int duration) {
        this.name = name;
        this.desc = desc;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getDuration() {
        return duration;
    }
}
