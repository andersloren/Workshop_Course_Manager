package se.lexicon.course_manager_assignment.data.service.student;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {StudentManager.class, CourseCollectionRepository.class, StudentCollectionRepository.class, ModelToDto.class})
public class StudentManagerTest {

    private CourseDao courseDao;
    private Converters converters;
    private StudentManager studentManager;

    @Autowired
    private StudentService testObject;
    @Autowired
    private StudentDao studentDao;


    @Test
    @DisplayName("New CreateStudentForm object should be the same as StudentView object.")
    void create() {



        CreateStudentForm createStudentForm = new CreateStudentForm(1, "Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");
        Student student = studentDao.createStudent(createStudentForm.getName(), createStudentForm.getEmail(), createStudentForm.getAddress());

        converters.studentToStudentView(student);

        Assertions.assertEquals(createStudentForm.getName(), student.getName());
        Assertions.assertEquals(createStudentForm.getEmail(), student.getEmail());
        Assertions.assertEquals(createStudentForm.getAddress(), student.getAddress());
    }

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(studentDao);
    }

    @Test
    @DisplayName("Returns student with the provided Id")
    void findById() {
        StudentManager studentManager1 = new StudentManager(studentDao, courseDao, converters);
        System.out.println(studentDao.findAll().toString());
        System.out.println( studentManager1.findById(1).toString());
    }

    @AfterEach
    void tearDown() {
        StudentSequencer.setStudentSequencer(0);
        studentDao.clear();
    }
}
