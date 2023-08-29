package se.lexicon.course_manager_assignment.data.service.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;

    @Test
    @DisplayName("A StudentView object from a Student object should hold the same content")
    void studentToStudentView() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");

        ModelToDto modelToDto = new ModelToDto();

        assertEquals(studentMikael.getId(), modelToDto.studentToStudentView(studentMikael).getId());
        System.out.println(modelToDto.studentToStudentView(studentMikael));
    } // This one is working

    @Test
    @DisplayName("")
    void courseToCourseView() { // This is working
        StudentCollectionRepository studentRepo = new StudentCollectionRepository(new HashSet<>());
        Student studentMikael = studentRepo.createStudent("Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");
        Student studentAnders = studentRepo.createStudent("Anders Loren", "anders@loren.org", "Jutevagen 22");

        CourseCollectionRepository courseRepo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = courseRepo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);

        courseJava.enrollStudent(studentMikael);
        courseJava.enrollStudent(studentAnders);

        ModelToDto modelToDto = new ModelToDto();
        assertEquals(courseJava.getCourseName(), modelToDto.courseToCourseView(courseJava).getCourseName());

        // Uncomment to see that: Java, Java
//        System.out.println(courseJava.getCourseName());
//        System.out.println(modelToDto.courseToCourseView(courseJava).getCourseName());
    }

    @Test
    @DisplayName("")
    void courseToCourseViews() {
        CourseCollectionRepository coursesRepo = new CourseCollectionRepository(new HashSet<Course>());
        Course courseJava = coursesRepo.createCourse("Java", LocalDate.of(2023, 8, 25), 12);

        ModelToDto modelToDto = new ModelToDto();

        assertEquals(courseJava.getCourseName(), modelToDto.courseToCourseView(courseJava).getCourseName());
    } // This is working

    @Test
    @DisplayName("") // TODO: Give this test a description
    void studentsToStudentViews() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");
        Student studentAnders = repo.createStudent("Anders Loren", "anders@loren.org", "Jutevagen 22");

        HashSet<Student> studentsHashSet = new HashSet<>();
        studentsHashSet.add(studentAnders);
        studentsHashSet.add(studentMikael);

        ModelToDto modelToDto = new ModelToDto();
        List<StudentView> studentsViewList = new ArrayList<>(modelToDto.studentsToStudentViews(studentsHashSet));
        String name = studentsViewList.get(0).getName();
        assertTrue(name.equalsIgnoreCase("Anders Loren") || name.equalsIgnoreCase("Mikael Engvall"));
    } // This one is working

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {

        assertNotNull(testObject);
    }


    //write your tests here
}
