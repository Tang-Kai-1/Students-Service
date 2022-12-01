package com.example.twentyeightstoneproject.students.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public Tutor(@NotNull String firstName, @NotNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Tutor(Long id, @NotNull String firstName, @NotNull String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Tutor() {
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

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tutor tutor)) return false;
        return id == tutor.id && firstName.equals(tutor.firstName) && lastName.equals(tutor.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
