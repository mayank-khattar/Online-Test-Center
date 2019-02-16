package com.mak.apps.Models;

public class Student {
    private String rollno;
    private String name;
    private String pass;
    private String course;
    private String dept;
    private String section;

    public Student(String rollno, String name, String pass, String course, String dept, String section) {
        this.rollno = rollno;
        this.name = name;
        this.pass = pass;
        this.course = course;
        this.dept = dept;
        this.section = section;
    }

    public String getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getCourse() {
        return course;
    }

    public String getDept() {
        return dept;
    }

    public String getSection() {
        return section;
    }
}
