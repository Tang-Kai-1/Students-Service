package com.example.twentyeightstoneproject.students;

import com.example.twentyeightstoneproject.students.domain.Course;
import com.example.twentyeightstoneproject.students.domain.Student;
import com.example.twentyeightstoneproject.students.domain.Tutor;
import com.example.twentyeightstoneproject.students.dto.CreateCourseRequest;
import com.example.twentyeightstoneproject.students.dto.CreateStudentRequest;
import com.example.twentyeightstoneproject.students.dto.CreateTutorRequest;
import com.example.twentyeightstoneproject.students.service.StudentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class StudentServiceTest {
    CreateTutorRequest tutorRequest = new CreateTutorRequest("Carl", "Johnson");
    @Autowired
    StudentService studentService;


    @Test
    @Transactional
    @DisplayName("Should be able to add Tutors, Courses, Students and retrieve course list by Student ID")
    void addStudents() {
        Tutor tutor1 = studentService.addTutor(new CreateTutorRequest("Carl", "Johnson")).getBody();
        Tutor tutor2 = studentService.addTutor(new CreateTutorRequest("John", "Doe")).getBody();
        Tutor tutor3 = studentService.addTutor(new CreateTutorRequest("Peter", "Parker")).getBody();
        Tutor tutor4 = studentService.addTutor(new CreateTutorRequest("Alfred", "Kohl")).getBody();
        System.out.println(tutor1.getFirstName());
        System.out.println(tutor2.getFirstName());
        System.out.println(tutor3.getFirstName());
        System.out.println(tutor4.getFirstName());
        Course course1 = studentService.addCourse(new CreateCourseRequest("English language", tutor1)).getBody();
        Course course2 = studentService.addCourse(new CreateCourseRequest("English literature", tutor1)).getBody();
        Course course3 = studentService.addCourse(new CreateCourseRequest("Linguistics", tutor1)).getBody();
        Course course4 = studentService.addCourse(new CreateCourseRequest("Computer architecture", tutor2)).getBody();
        Course course5 = studentService.addCourse(new CreateCourseRequest("History", tutor3)).getBody();
        Course course6 = studentService.addCourse(new CreateCourseRequest("Ethics", tutor3)).getBody();
        Course course7 = studentService.addCourse(new CreateCourseRequest("Algebra", tutor4)).getBody();
        Course course8 = studentService.addCourse(new CreateCourseRequest("Geometry", tutor4)).getBody();
        Student student1 = studentService.addStudent(new CreateStudentRequest("Frank", "Peterson", new ArrayList<Course>(Arrays.asList(course4, course5, course7)))).getBody();
        Student student2 = studentService.addStudent(new CreateStudentRequest("Anna", "Larson", new ArrayList<Course>(Arrays.asList(course1, course2, course3, course8)))).getBody();
        assertTrue(tutor1 != null);
        assertTrue(tutor2 != null);
        assertTrue(tutor3 != null);
        assertTrue(tutor4 != null);
        assertTrue(course1 != null);
        assertTrue(course2 != null);
        assertTrue(course3 != null);
        assertTrue(course4 != null);
        assertTrue(course5 != null);
        assertTrue(course6 != null);
        assertTrue(course7 != null);
        assertTrue(course8 != null);
        assertTrue(student1 != null);
        assertTrue(student2 != null);
        List<Course> student1ReceivedCourseList = studentService.getStudent(student1.getId()).getBody();
        List<Course> student2ReceivedCourseList = studentService.getStudent(student2.getId()).getBody();
        assertEquals(student1.getCourseList(),student1ReceivedCourseList);
        assertEquals(student2.getCourseList(),student2ReceivedCourseList);
    }

    @Test
    @Transactional
    @DisplayName("Should not be able to add Tutors with same names")
    void cannotAddSameTutorTest() {
        studentService.addTutor(tutorRequest);
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            studentService.addTutor(tutorRequest);
        });
        assertEquals("Tutor already exists!", thrown.getReason());
    }

    @Test
    @Transactional
    @DisplayName("Should not be able to add same Course")
    void cannotAddSameCourseTest() {
        CreateCourseRequest courseRequest = new CreateCourseRequest("English", studentService.addTutor(tutorRequest).getBody());
        studentService.addCourse(courseRequest);
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            studentService.addCourse(courseRequest);
        });
        assertEquals("Course already exists!", thrown.getReason());
    }
    @Test
    @Transactional
    @DisplayName("Should not be able to add Course with non-existent Tutor")
    void cannotAddCourseWithInvalidTutorTest(){
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            studentService.addCourse(new CreateCourseRequest("English", new Tutor("Doesn't", "Exist")));
        });
        assertEquals("Tutor not found!", thrown.getReason());
    }
    @Test
    @Transactional
    @DisplayName("Should not be able to add Student with non-existent Courses")
    void cannotAddStudentWithInvalidCourseTest(){
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            studentService.addStudent(new CreateStudentRequest(
                    "Frank","Gallager", new ArrayList<Course>(
                            Arrays.asList(new Course("English",new Tutor("Doesn't", "Exist"))))));
        });
        assertEquals("Course not found, student cannot be added!", thrown.getReason());
    }
}
