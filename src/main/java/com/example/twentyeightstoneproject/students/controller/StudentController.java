package com.example.twentyeightstoneproject.students.controller;

import com.example.twentyeightstoneproject.students.service.StudentService;
import com.example.twentyeightstoneproject.students.domain.Course;
import com.example.twentyeightstoneproject.students.domain.Student;
import com.example.twentyeightstoneproject.students.domain.Tutor;
import com.example.twentyeightstoneproject.students.dto.CreateCourseRequest;
import com.example.twentyeightstoneproject.students.dto.CreateStudentRequest;
import com.example.twentyeightstoneproject.students.dto.CreateTutorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PutMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return service.addStudent(createStudentRequest);
    }

    @PutMapping("/course")
    public ResponseEntity<Course> addCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        return service.addCourse(createCourseRequest);
    }

    @PutMapping("/tutor")
    public ResponseEntity<Tutor> addTutor(@RequestBody CreateTutorRequest createTutorRequest) {
        return service.addTutor(createTutorRequest);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Course>> getStudent(@PathVariable("id") int id) {
        return service.getStudent(id);
    }
}
