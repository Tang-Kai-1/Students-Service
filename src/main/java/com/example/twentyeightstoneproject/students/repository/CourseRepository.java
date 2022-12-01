package com.example.twentyeightstoneproject.students.repository;

import com.example.twentyeightstoneproject.students.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
