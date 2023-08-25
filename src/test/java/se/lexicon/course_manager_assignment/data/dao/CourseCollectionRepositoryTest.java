package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    @Test
    @DisplayName("Course object created from createCourse should be given consecutive id values")
    void createNewStudent() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course course1 = repo.createCourse("Java", LocalDate.of(2023,8,25),12);
        Course course2 = repo.createCourse("Python", LocalDate.of(2023,10,25),14);

        assertEquals(1, course1.getId());
        assertEquals(2, course2.getId());

        // Uncomment these for proof that: 1, 2
//        System.out.println(course1.getId());
//        System.out.println(course2.getId());
    }

    @Test
    @DisplayName("New CourseCollectionRepository object should have courses added to the Collection")
    void createNewCourse() {
        CourseCollectionRepository repo = new CourseCollectionRepository(new HashSet<Course>());
        Course course1 = repo.createCourse("Java", LocalDate.of(2023,8,25),12);
        Course course2 = repo.createCourse("Python", LocalDate.of(2023,10,25),14);

        System.out.println((repo.findAll()));
        //Todo: This pretty much let us know if students have been added to the repo
    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
