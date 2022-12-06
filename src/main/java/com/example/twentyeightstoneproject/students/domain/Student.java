package com.example.twentyeightstoneproject.students.domain;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "student_course_list",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courseList;

    public Student(@NotNull String firstName, @NotNull String lastName, List<Course> courseList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseList = courseList;
    }

    public Student() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return firstName.equals(student.firstName) && lastName.equals(student.lastName) && courseList.equals(student.courseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, courseList);
    }
}
