package com.example.twentyeightstoneproject.students.domain;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;


@Entity
public class Course {
    @Id
    private String courseName;
    @ManyToOne
    private Tutor tutor;

    public Course(@NotNull String courseName, Tutor tutor) {
        this.courseName = courseName;
        this.tutor = tutor;
    }

    public Course() {

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return courseName.equals(course.courseName) && tutor.equals(course.tutor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, tutor);
    }
}
