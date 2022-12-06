package com.example.twentyeightstoneproject.students.service;

import com.example.twentyeightstoneproject.students.domain.Course;
import com.example.twentyeightstoneproject.students.domain.Student;
import com.example.twentyeightstoneproject.students.domain.Tutor;
import com.example.twentyeightstoneproject.students.dto.CreateCourseRequest;
import com.example.twentyeightstoneproject.students.dto.CreateStudentRequest;
import com.example.twentyeightstoneproject.students.dto.CreateTutorRequest;
import com.example.twentyeightstoneproject.students.repository.CourseRepository;
import com.example.twentyeightstoneproject.students.repository.StudentRepository;
import com.example.twentyeightstoneproject.students.repository.TutorsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final TutorsRepository tutorsRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, TutorsRepository tutorsRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.tutorsRepository = tutorsRepository;
        this.courseRepository = courseRepository;
    }

    public ResponseEntity<Student> addStudent(CreateStudentRequest createStudentRequest) {
        if (createStudentRequest.getCourses().stream().anyMatch(c -> courseRepository.findById(c.getCourseName()).isEmpty())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found, student cannot be added!");
        }
        List<Course> courses = new ArrayList<>();
        for (Course course : createStudentRequest.getCourses()) {
            courseRepository.findById(course.getCourseName()).ifPresent(courses::add);
        }

        Student student = new Student(createStudentRequest.getFirstName(), createStudentRequest.getLastName(), courses);
        return new ResponseEntity<>(studentRepository.saveAndFlush(student), HttpStatus.OK);
    }

    public ResponseEntity<Course> addCourse(CreateCourseRequest createCourseRequest) {
        if (courseRepository.findAll().stream().anyMatch(
                c -> c.getCourseName().equals(createCourseRequest.getCourseName()) &&
                        c.getTutor().equals(createCourseRequest.getTutor()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists!");
        }
        Tutor tutor = tutorsRepository.findById(createCourseRequest.getTutor().getId()).orElse(null);
        if (tutor != null) {
            Course course = new Course(createCourseRequest.getCourseName(), tutor);
            return new ResponseEntity<>(courseRepository.saveAndFlush(course), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor not found!");
    }

    public ResponseEntity<Tutor> addTutor(CreateTutorRequest createTutorRequest) {
        if (tutorsRepository.findAll().stream().anyMatch(
                t -> t.getFirstName().equals(createTutorRequest.getFirstName()) &&
                        t.getLastName().equals(createTutorRequest.getLastName()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tutor already exists!");
        }
        Tutor tutor = new Tutor(createTutorRequest.getFirstName(), createTutorRequest.getLastName());
        return new ResponseEntity<>(tutorsRepository.saveAndFlush(tutor), HttpStatus.OK);
    }

    public ResponseEntity<List<Course>> getStudent(long id) {
        List<Course> courseList = studentRepository.findById(id).orElse(null).getCourseList();
        if (courseList != null) {
            return new ResponseEntity<>(courseList, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!");
    }
}
