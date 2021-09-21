package com.rashik.cgpa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity
public class Semester {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String semesterName;
    public Double semesterCredit;
   // List<Course>semesterCourses;



    public Semester(String semesterName, Double semesterCredit) {
        this.semesterName = semesterName;
        this.semesterCredit = semesterCredit;
        //this.semesterCourses = semesterCourses;
    }

    public int getId() {
        return id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Double getSemesterCredit() {
        return semesterCredit;
    }

    public void setSemesterCredit(Double semesterCredit) {
        this.semesterCredit = semesterCredit;
    }

}
