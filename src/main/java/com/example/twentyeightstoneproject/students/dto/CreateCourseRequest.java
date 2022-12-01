package com.example.twentyeightstoneproject.students.dto;

import com.example.twentyeightstoneproject.students.domain.Tutor;

public class CreateCourseRequest {
    private String courseName;
    private Tutor tutor;

    public CreateCourseRequest(String courseName, Tutor tutor) {
        this.courseName = courseName;
        this.tutor = tutor;
    }

    public String getCourseName() {
        return courseName;
    }

    public Tutor getTutor() {
        return tutor;
    }
}
