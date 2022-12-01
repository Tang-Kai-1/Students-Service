package com.example.twentyeightstoneproject.students.repository;

import com.example.twentyeightstoneproject.students.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
