package com.example.twentyeightstoneproject.students.repository;

import com.example.twentyeightstoneproject.students.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorsRepository extends JpaRepository<Tutor, Long> {
}
