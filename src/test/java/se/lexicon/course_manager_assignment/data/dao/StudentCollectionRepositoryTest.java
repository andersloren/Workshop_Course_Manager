package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Student object created from createStudent should be given consecutive id values")
    void createNewStudentHasId() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student student1 = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student student2 = repo.createStudent("Anders Engvall", "mikael@engvall.se", "Vallgatan 22");

        assertEquals(1, student1.getId());
        assertEquals(2, student2.getId());

        // Uncomment these for proof that: 1, 2
//        System.out.println(student1.getId());
//        System.out.println(student2.getId());
    }

    @Test
    @DisplayName("New StudentCollectionRepository object should have students added to the Collection")
    void createNewStudent() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student student1 = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student student2 = repo.createStudent("Anders Engvall", "mikael@engvall.se", "Vallgatan 22");

        System.out.println((repo.findAll()));
        //Todo: This pretty much let us know if students have been added to the repo
    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
