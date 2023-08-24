package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CourseTest {

    @Test
    @DisplayName("Enroll Student object with valid strings in all fields")
    void enrollStudentWhenAllFieldsAreStrings() {
        Student student = new Student("Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");

        Course course = new Course();
        Assertions.assertTrue(course.enrollStudent(student));
    }

    @ParameterizedTest
    @DisplayName("Enroll Student object with one field set to null")
    @CsvSource(value = {
            "null, m@k.org, V22",
            "ME, null, V22",
            "ME, m@k.org, null"
    }, nullValues = {"null"})
    void enrollStudentWhenNull(String name, String email, String address) {
        Student student = new Student(name, email, address);
        Course course = new Course();

        Assertions.assertFalse(course.enrollStudent(student));
    }

    @Test
    @DisplayName("Unroll student that exists in course")
    void unrollStudentThatAlreadyExist() {
        Student student = new Student("Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");

        Course course = new Course();
        course.enrollStudent(student);
        Assertions.assertTrue(course.unrollStudent(student));
    }

    @Test
    @DisplayName("Unroll student that does not exist in course")
    void unrollStudent() {
        Student mikael = new Student("Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");
        Student anders = new Student("Anders Loren", "anders@loren.org", "Jutevagen 22");

        Course course = new Course();
        course.enrollStudent(mikael);
        Assertions.assertFalse(course.unrollStudent(anders));
    }
}
