package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import javax.xml.parsers.SAXParser;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Student object created from createStudent should be given consecutive id values")
    void createNewStudentHasId() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 5");

        assertEquals(1, studentMikael.getId());
        assertEquals(2, studentAnders.getId());

        // Uncomment these for proof that: 1, 2
//        System.out.println(student1.getId());
//        System.out.println(student2.getId());
    }

    @Test
    @DisplayName("New StudentCollectionRepository object should have students added to a HashSet")
    void createStudent() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 5");

        System.out.println(repo.findAll());
    }

    @Test
    @DisplayName("Returns student whose email matches an existing Student object's email in in the StudentCollectionRepository object ")
    void findEmailIgnoreCase() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 5");

        String mikaelEmail = "mikael@engvall.se";
        Student student = repo.findByEmailIgnoreCase(mikaelEmail);
        assertEquals(studentMikael, student);
        System.out.println("Existerande: \t" + student.toString() + ", \nHittad: \t\t" + studentMikael.toString());
    }

    @Test
    @DisplayName("Returns entire repository if name exists among Student objects in in the StudentCollectionRepository object ")
    void findByNameContains() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 5");
        Student studentAndersEngvall = repo.createStudent("Anders Engvall", "anders@engvall.se", "Jutegatan 25");

        HashSet<Student> studentsAllContainingAnders = new HashSet<>();
        studentsAllContainingAnders.add(studentAnders);
        studentsAllContainingAnders.add(studentAndersEngvall);

        String studentName = "Anders";
        HashSet<Student> students = new HashSet<>(repo.findByNameContains(studentName));
        assertEquals(studentsAllContainingAnders, students);
    }

    @Test
    @DisplayName("Returns Student object whose Id matches an existing Student object's Id in the StudentCollectionRepository hashSet")
    void findById() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 5");

        Student student = repo.findById(1);
        assertEquals(studentMikael, student);
        System.out.println("Existerande: \t" + student.toString() + ", \nHittad: \t\t" + studentMikael.toString());
    }

    @Test
    @DisplayName("Returns entire array in StudentCollectionRepository ")
    void findAll() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 5");

        HashSet<Student> studentsAll = new HashSet<Student>();
        studentsAll.add(studentAnders);
        studentsAll.add(studentMikael);

        HashSet<Student> students = new HashSet<>(repo.findAll());
        System.out.println(students); // Just shows that the code above is working
        assertEquals(studentsAll, students);
    }


    @Test
    @DisplayName("Removes Student object that matches an existing Student object's email in the StudentCollectionRepository object")
    void removeStudent() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.se", "Jutevägen 5");

        HashSet<Student> students = new HashSet<>(repo.findAll());
        System.out.println(students);
        boolean b = (repo.removeStudent(studentAnders));
        assertTrue(b);
        System.out.println(students = new HashSet<>(repo.findAll()));
    }

    @Test
    @DisplayName("Clear all Student objects from StudentCollectionRepository HashSet")
    void clear() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");

        HashSet<Student> students = new HashSet<>(repo.findAll());
        repo.clear();
        students = new HashSet<>(repo.findAll());
        assertTrue(students.isEmpty());
    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
