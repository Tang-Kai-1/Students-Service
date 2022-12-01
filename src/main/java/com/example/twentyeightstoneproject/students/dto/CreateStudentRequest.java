package com.example.twentyeightstoneproject.students.dto;

import com.example.twentyeightstoneproject.students.domain.Course;

import java.util.List;


public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private List<Course> courses;

    public CreateStudentRequest(String firstName, String lastName, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
