package com.rashik.cgpa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
            public int id;

    public String courseName;
    public Double courseGpa;
    public Double courseCredit;
    public int semesterId;



    public Course( Double courseGpa, Double courseCredit, int semesterId) {

        this.courseGpa = courseGpa;
        this.courseCredit = courseCredit;
        this.semesterId = semesterId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    /*public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }*/

    public Double getCourseGpa() {
        return courseGpa;
    }

    public void setCourseGpa(Double courseGpa) {
        this.courseGpa = courseGpa;
    }

    public Double getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Double courseCredit) {
        this.courseCredit = courseCredit;
    }


}
