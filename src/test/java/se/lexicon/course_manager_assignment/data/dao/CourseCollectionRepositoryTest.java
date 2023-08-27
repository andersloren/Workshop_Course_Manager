package se.lexicon.course_manager_assignment.data.dao;

import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import javax.annotation.processing.SupportedAnnotationTypes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;

    //Write your tests here
    @Test
    @DisplayName("Course object created from createCourse should be given consecutive id values")
    void createNewStudentHasId() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course coursePython = repo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        assertEquals(1, courseJava.getId());
        assertEquals(2, coursePython.getId());

        // Uncomment these for proof that: 1, 2
        System.out.println(courseJava.getId());
        System.out.println(coursePython.getId());
    }

    @Test
    @DisplayName("New CourseCollectionRepository object should have courses added to a HashSet")
    void createCourse() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course coursePython = repo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        System.out.println(repo.findAll());
    }

    @Test
    @DisplayName("Returns Course object whose Id matches an existing Course object's Id in the CourseCollectionRepository hashSet")
    void findById() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course coursePython = repo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        Course course = repo.findById(1);
        assertEquals(courseJava, course);
    }

    @Test
    @DisplayName("Returns entire repository if name exists among Student objects in in the StudentCollectionRepository object ")
    void findByNameContains() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course courseJavaScript = repo.createCourse("JavaScript", LocalDate.of(2023, 11, 25), 12);
        Course coursePython = repo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        HashSet<Course> coursesContainsJava = new HashSet<>();
        coursesContainsJava.add(courseJava);
        coursesContainsJava.add(courseJavaScript);

        String courseName = "Java";
        HashSet<Course> courses = new HashSet<>(repo.findByNameContains(courseName));
        System.out.println(courses);
        assertEquals(coursesContainsJava, courses);
    }


    @Test
    @DisplayName("Returns all Course objects that starts before a date")
    void findByDateBefore() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course coursePython = repo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        HashSet<Course> courseJavaHashSet = new HashSet<>();
        courseJavaHashSet.add(courseJava); // starts before September 1st

        HashSet<Course> coursesBeforeSeptemberFirst = new HashSet<>(repo.findByDateBefore(LocalDate.of(2023,9,1)));

        assertEquals(courseJavaHashSet, coursesBeforeSeptemberFirst);
    }

    @Test
    @DisplayName("Returns all Course objects that starts after a date")
    void findByDateAfter() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course coursePython = repo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        HashSet<Course> coursePythonHashSet = new HashSet<>();
        coursePythonHashSet.add(coursePython);

        HashSet<Course> coursesStartingAfterSeptember = new HashSet<>(repo.findByDateAfter(LocalDate.of(2023,9,1)));

        assertEquals(coursePythonHashSet, coursesStartingAfterSeptember);
    }

    @Test
    @DisplayName("Returns entire array in CourseCollectionRepository")
    void findAll() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course coursePython = repo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        HashSet<Course> courses = new HashSet<Course>();
        courses.add(courseJava);
        courses.add(coursePython);

        HashSet<Course> coursesFindAll = new HashSet<>(repo.findAll());
        System.out.println(courses); // Just shows that the code above is working
        assertEquals(courses, coursesFindAll);
    }

    @Test
    @DisplayName("Returns array with Course objects that Student object's Id is enrolled to")
    void findByStudentId() {
        CourseCollectionRepository coursesRepo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = coursesRepo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course coursePython = coursesRepo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        StudentCollectionRepository studentsRepo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = studentsRepo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = studentsRepo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 22");

        courseJava.enrollStudent(studentMikael);
        courseJava.enrollStudent(studentAnders);
        coursePython.enrollStudent(studentMikael);

        HashSet<Course> coursesJava = new HashSet<>();
        coursesJava.add(courseJava);

        HashSet<Course> coursesJavaPython = new HashSet<>();
        coursesJavaPython.add(courseJava);
        coursesJavaPython.add(coursePython);

        HashSet<Course> coursesMikael = new HashSet<>(coursesRepo.findByStudentId(studentMikael.getId()));
        HashSet<Course> coursesAnders = new HashSet<>(coursesRepo.findByStudentId(studentAnders.getId()));

        assertEquals(coursesJavaPython, coursesMikael);
        assertEquals(coursesJava, coursesAnders);
    }

    @Test
    @DisplayName("Removes Course object from a CourseCollectionRepository object")
    void removeCourse() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
        Course coursePython = repo.createCourse("Python", LocalDate.of(2023, 10, 25), 14);

        HashSet<Course> courses = new HashSet<>(repo.findAll());
        System.out.println(courses);
        boolean b = (repo.removeCourse(coursePython));
        Assertions.assertTrue(b);
        System.out.println(courses = new HashSet<>(repo.findAll()));
    }

    @Test
    @DisplayName("Clear all Course objects from a CourseCollectionRepository HashSet")
    void clear() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = repo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);

        HashSet<Course> courses = new HashSet<>(repo.findAll());
        repo.clear();
        courses = new HashSet<>(repo.findAll());
        assertTrue(courses.isEmpty());
    }
    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
