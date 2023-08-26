package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Student object created from createStudent should be given consecutive id values")
    void createNewStudentHasId() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 22");

        assertEquals(1, studentMikael.getId());
        assertEquals(2, studentAnders.getId());

        // TODO: Show this to the group
//        System.out.println(student1.getId());
//        System.out.println(student2.getId());
    }

    @Test
    @DisplayName("New StudentCollectionRepository object should have students added to the Collection")
    void createNewStudent() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 22");

        System.out.println((repo.findAll()));
        //// TODO: Show this to the group
    }

    @Test
    @DisplayName("Returns student whose email matches an existing Student object's email in StudentCollectionRepository ")
    void findEmailIgnoreCase() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 22");

        String mikaelEmail = "mikael@engvall.se";
        Student student = repo.findByEmailIgnoreCase(mikaelEmail);
        assertEquals(studentMikael, student);
        // TODO: Show this to the group
        System.out.println("Existerande: \t" + student.toString() + ", \nHittad: \t\t" + studentMikael.toString());
    }

    @Test
    @DisplayName("Returns entire repository if name exists among Student objects in StudentCollectionRepository ")
    void findByNameContains() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 22");

        String studentName = "Mikael Engvall";
        ArrayList<Student> students = new ArrayList<>(repo.findByNameContains(studentName));
        System.out.println(students); // Just shows that the code above is working
//        assertEquals(???, ???)
//        TODO: How should we assert that the returned list is correct?
    }

    @Test
    @DisplayName("Returns Student object whose Id matches an existing Student object's Id in StudentCollectionRepository ")
    void findById() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 22");

        Student student = repo.findById(1);
        assertEquals(studentMikael, student);
        // TODO: Show this to the group
        System.out.println("Existerande: \t" + student.toString() + ", \nHittad: \t\t" + studentMikael.toString());
    }

    @Test
    @DisplayName("Returns entire array in StudentCollectionRepository ")
    void findAll() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 22");

        HashSet<Student> students = new HashSet<>(repo.findAll());
        System.out.println(students); // Just shows that the code above is working
//        assertEquals(???, ???)
//        TODO: How should we assert that the returned list is correct?
    }


    @Test
    @DisplayName("Returns student whose email matches an existing Student object's email in StudentCollectionRepository ")
    void removeStudent() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 22");

        HashSet<Student> students = new HashSet<>(repo.findAll());
        System.out.println(students);
        boolean b = (repo.removeStudent(studentAnders));
        assertTrue(b);
        System.out.println(students = new HashSet<>(repo.findAll()));
        // TODO: THIS IS NOT DONE!!!!
    }


    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
