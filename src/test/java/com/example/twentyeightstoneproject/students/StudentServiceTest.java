package com.example.twentyeightstoneproject.students;

import com.example.twentyeightstoneproject.students.domain.Course;
import com.example.twentyeightstoneproject.students.domain.Student;
import com.example.twentyeightstoneproject.students.domain.Tutor;
import com.example.twentyeightstoneproject.students.dto.CreateCourseRequest;
import com.example.twentyeightstoneproject.students.dto.CreateStudentRequest;
import com.example.twentyeightstoneproject.students.dto.CreateTutorRequest;
import com.example.twentyeightstoneproject.students.repository.CourseRepository;
import com.example.twentyeightstoneproject.students.repository.StudentRepository;
import com.example.twentyeightstoneproject.students.repository.TutorsRepository;
import com.example.twentyeightstoneproject.students.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    TutorsRepository tutorsRepository;
    @Mock
    CourseRepository courseRepository;
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;


    private final Tutor tutor1 = new Tutor("John", "Blank");

    private final Tutor tutor2 = new Tutor("Adam", "Smith");
    private final Course course1 = new Course("Math", tutor1);
    private final Course course2 = new Course("Computer architecture", tutor1);
    private final Course course3 = new Course("English language", tutor2);
    private final Course course4 = new Course("English literature", tutor2);
    private final Course course5 = new Course("Linguistics", tutor2);
    private final Student student1 = new Student("First", "Name", new ArrayList<>(Arrays.asList(course1, course2)));
    private final Student student2 = new Student("Name", "Surname", new ArrayList<>(Arrays.asList(course3, course4, course5)));

    @Test
    @DisplayName("Should be able to add Tutors, Courses, Students and retrieve course list by Student ID")
    void addStudents() {
        tutor1.setId(1);
        tutor2.setId(2);
        student1.setId(1);
        student2.setId(2);
        lenient().when(tutorsRepository.saveAndFlush(new Tutor(tutor1.getFirstName(), tutor1.getLastName()))).thenReturn(tutor1);
        lenient().when(tutorsRepository.saveAndFlush(new Tutor(tutor2.getFirstName(), tutor2.getLastName()))).thenReturn(tutor2);
        lenient().when(courseRepository.saveAndFlush(new Course(course1.getCourseName(), course1.getTutor()))).thenReturn(course1);
        lenient().when(courseRepository.saveAndFlush(new Course(course2.getCourseName(), course2.getTutor()))).thenReturn(course2);
        lenient().when(courseRepository.saveAndFlush(new Course(course3.getCourseName(), course3.getTutor()))).thenReturn(course3);
        lenient().when(courseRepository.saveAndFlush(new Course(course4.getCourseName(), course4.getTutor()))).thenReturn(course4);
        lenient().when(courseRepository.saveAndFlush(new Course(course5.getCourseName(), course5.getTutor()))).thenReturn(course5);
        lenient().when(studentRepository.saveAndFlush(new Student(student1.getFirstName(), student1.getLastName(), student1.getCourseList()))).thenReturn(student1);
        lenient().when(studentRepository.saveAndFlush(new Student(student2.getFirstName(), student2.getLastName(), student2.getCourseList()))).thenReturn(student2);
        lenient().when(tutorsRepository.findById(tutor1.getId())).thenReturn(Optional.of(tutor1));
        lenient().when(tutorsRepository.findById(tutor2.getId())).thenReturn(Optional.of(tutor2));
        lenient().when(courseRepository.findById(course1.getCourseName())).thenReturn(Optional.of(course1));
        lenient().when(courseRepository.findById(course2.getCourseName())).thenReturn(Optional.of(course2));
        lenient().when(courseRepository.findById(course3.getCourseName())).thenReturn(Optional.of(course3));
        lenient().when(courseRepository.findById(course4.getCourseName())).thenReturn(Optional.of(course4));
        lenient().when(courseRepository.findById(course5.getCourseName())).thenReturn(Optional.of(course5));
        lenient().when(studentRepository.findById(student1.getId())).thenReturn(Optional.of(student1));
        lenient().when(studentRepository.findById(student2.getId())).thenReturn(Optional.of(student2));

        Tutor tutorSaved1 = studentService.addTutor(new CreateTutorRequest(tutor1.getFirstName(), tutor1.getLastName())).getBody();
        Tutor tutorSaved2 = studentService.addTutor(new CreateTutorRequest(tutor2.getFirstName(), tutor2.getLastName())).getBody();
        Course courseSaved1 = studentService.addCourse(new CreateCourseRequest(course1.getCourseName(), course1.getTutor())).getBody();
        Course courseSaved2 = studentService.addCourse(new CreateCourseRequest(course2.getCourseName(), course1.getTutor())).getBody();
        Course courseSaved3 = studentService.addCourse(new CreateCourseRequest(course3.getCourseName(), course3.getTutor())).getBody();
        Course courseSaved4 = studentService.addCourse(new CreateCourseRequest(course4.getCourseName(), course4.getTutor())).getBody();
        Course courseSaved5 = studentService.addCourse(new CreateCourseRequest(course5.getCourseName(), course5.getTutor())).getBody();
        Student studentSaved1 = studentService.addStudent(new CreateStudentRequest(student1.getFirstName(), student1.getLastName(), student1.getCourseList())).getBody();
        Student studentSaved2 = studentService.addStudent(new CreateStudentRequest(student2.getFirstName(), student2.getLastName(), student2.getCourseList())).getBody();

        assertTrue(tutorSaved1.equals(tutor1));
        assertTrue(tutorSaved2.equals(tutor2));
        assertTrue(courseSaved1.equals(course1));
        assertTrue(courseSaved2.equals(course2));
        assertTrue(courseSaved3.equals(course3));
        assertTrue(courseSaved4.equals(course4));
        assertTrue(courseSaved5.equals(course5));
        assertTrue(studentSaved1.equals(student1));
        assertTrue(studentSaved2.equals(student2));
        List<Course> student1ReceivedCourseList = studentService.getStudent(student1.getId()).getBody();
        List<Course> student2ReceivedCourseList = studentService.getStudent(student2.getId()).getBody();
        assertEquals(student1.getCourseList(), student1ReceivedCourseList);
        assertEquals(student2.getCourseList(), student2ReceivedCourseList);

    }

    @Test
    @DisplayName("Should not be able to add Tutors with same names")
    void cannotAddSameTutorTest() {
        lenient().when(tutorsRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(tutor1)));
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            studentService.addTutor(new CreateTutorRequest(tutor1.getFirstName(), tutor1.getLastName()));
        });
        assertEquals("Tutor already exists!", thrown.getReason());
    }

    @Test
    @DisplayName("Should not be able to add same Course")
    void cannotAddSameCourseTest() {
        lenient().when(courseRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(course1)));
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            studentService.addCourse(new CreateCourseRequest(course1.getCourseName(), course1.getTutor()));
        });
        assertEquals("Course already exists!", thrown.getReason());
    }

    @Test
    @DisplayName("Should not be able to add Course with non-existent Tutor")
    void cannotAddCourseWithInvalidTutorTest() {
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            studentService.addCourse(new CreateCourseRequest("English", new Tutor("Doesn't", "Exist")));
        });
        assertEquals("Tutor not found!", thrown.getReason());
    }

    @Test
    @DisplayName("Should not be able to add Student with non-existent Courses")
    void cannotAddStudentWithInvalidCourseTest() {
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            studentService.addStudent(new CreateStudentRequest(
                    "Frank", "Gallager", new ArrayList<Course>(
                    Arrays.asList(new Course("English", new Tutor("Doesn't", "Exist"))))));
        });
        assertEquals("Course not found, student cannot be added!", thrown.getReason());
    }
}
