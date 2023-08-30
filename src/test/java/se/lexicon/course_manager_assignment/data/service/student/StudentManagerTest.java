package se.lexicon.course_manager_assignment.data.service.student;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StudentManager.class, CourseCollectionRepository.class, StudentCollectionRepository.class, ModelToDto.class})
public class StudentManagerTest {

    private CourseDao courseDao;
    private Converters converters;
    private StudentManager studentManager;
        private Student student;

    @Autowired
    private StudentService testObject;
    @Autowired
    private StudentDao studentDao;


    @BeforeEach
    public void setUp() {
        student = studentDao.createStudent("Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");
    }
    @Test
    @DisplayName("New CreateStudentForm object should be the same as StudentView object.")
    void create() {
        CreateStudentForm createStudentForm = new CreateStudentForm(1, "Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");
        StudentView studentView = testObject.create(createStudentForm);

        Assertions.assertEquals(studentView.getName(), createStudentForm.getName());
        Assertions.assertEquals(studentView.getEmail(), createStudentForm.getEmail());
        Assertions.assertEquals(studentView.getAddress(), createStudentForm.getAddress());
    }

    @Test
    @DisplayName("Returns StudentView object of the student belonging to the provided Id")
    void findById() {
        int id = 1;
        assertEquals(student.getId(), testObject.findById(id).getId());
    }

    @Test
    @DisplayName("Returns StudentView object with specific email")
    void searchByEmail() {
        String email = "mikael@engvall.org";
        assertEquals(student.getEmail(), testObject.searchByEmail(email).getEmail());
    }

    @Test
    @DisplayName("Returns list of StudentView objects that has the same name")
    void searchByName() {
        String name = "mikael engvall";
        assertEquals(student.getName(), testObject.searchByName(name).get(0).getName());
    }

    @Test
    @DisplayName("Returns list of all StudentView objects")
    void findAll() {
        assertEquals(student.getName(), testObject.findAll().get(0).getName());
    }

    @Test
    @DisplayName("Returns true if Student object got deleted")
    void deleteStudent() {
        int id = 1;
        assertTrue(testObject.deleteStudent(id));
    }

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {

        assertNotNull(testObject);
        assertNotNull(studentDao);
    }

    @AfterEach
    void tearDown() {
        StudentSequencer.setStudentSequencer(0);
        studentDao.clear();
    }
}
