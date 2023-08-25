package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;

import java.util.HashSet;

public class StudentTest {
    @Test
    void getId() {
    }

    @Test
    void getName() {
        Student student = new Student(StudentSequencer.nextStudentId(), "Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");
        Assertions.assertEquals(1,"Mikael Engvall", student.getName());
    }

    @Test
    void setName() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void setAddress() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}
