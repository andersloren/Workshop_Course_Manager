package se.lexicon.course_manager_assignment.data.sequencers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.course_manager_assignment.controller.StudentControllerTest;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentSequencerTest {

    // This method ensures that setStudentSequencer is set to 0.
    // It runs BEFORE EACH test in this test class.
    // This is sometimes called a "Setup method".
    @BeforeEach
    void setUp() {
        StudentSequencer.setStudentSequencer(0);
    }

    @Test
    @DisplayName("nextStudentId should give consecutive numbers")
    void giveIdToStudentObjects() {
        int id1 = StudentSequencer.nextStudentId();
        int id2 = StudentSequencer.nextStudentId();
        int id3 = StudentSequencer.nextStudentId();

        assertEquals(1, id1);
        assertEquals(2, id2);
        assertEquals(3, id3);
    }

    @Test
    @DisplayName("getStudentSequencer() should return 0")
    void getStudentSequencer_return_0() {
        assertEquals(0, StudentSequencer.getStudentSequencer());
    }

    @Test
    @DisplayName("nextStudentId() should return 1")
    void nextStudentId_return_1() {
        assertEquals(1, StudentSequencer.nextStudentId());
    }
}
