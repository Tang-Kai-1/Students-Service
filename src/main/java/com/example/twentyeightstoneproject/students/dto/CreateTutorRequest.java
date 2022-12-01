package com.example.twentyeightstoneproject.students.dto;

public class CreateTutorRequest {
    private String firstName;
    private String lastName;

    public CreateTutorRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
