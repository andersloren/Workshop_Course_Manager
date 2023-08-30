package se.lexicon.course_manager_assignment.data.service.course;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {CourseManager.class, CourseCollectionRepository.class, ModelToDto.class, StudentCollectionRepository.class})
public class CourseManagerTest {

    private Course course;
    private Student student;

    @Autowired
    private CourseService testObject;

    @Autowired
    private CourseDao courseDao;

    @BeforeEach
    public void setUp() {
        course = courseDao.createCourse("Java", LocalDate.of(2023, 8, 25), 12);
    }

    @Test
    void create() {
        CreateCourseForm createCourseForm = new CreateCourseForm(1, "Java", LocalDate.of(2023,8,25), 12);
        CourseView courseView = testObject.create(createCourseForm);

        assertEquals(course.getCourseName(), courseView.getCourseName());
        assertEquals(course.getStartDate(), courseView.getStartDate());
        assertEquals(course.getWeekDuration(), courseView.getWeekDuration());
    }

    @Test
    @DisplayName("Returns updates CourseView object")
    void update() {
        CreateCourseForm createCourseForm = new CreateCourseForm(1, "Java", LocalDate.of(2023,8,25), 12);
        testObject.create(createCourseForm);

        UpdateCourseForm updateCourseForm = new UpdateCourseForm(2, "Python", LocalDate.of(2023, 9, 1), 13);
        testObject.update(updateCourseForm);
        assertEquals(updateCourseForm.getCourseName(), testObject.update(updateCourseForm).getCourseName());
    }

    @Test
    @DisplayName("Returns list of courses that includes the name passed")
    void searchByCourseName() {
        CreateCourseForm python1 = new CreateCourseForm(2, "Python, from zero to hero", LocalDate.of(2023, 9, 1), 25);
        CreateCourseForm python2 = new CreateCourseForm(3, "Python, from hero to zen", LocalDate.of(2024, 1, 1), 100);
        testObject.create(python1);
        testObject.create(python2); // 2 courses in total that includes the name "Python"
        String courseName = "Python";
        assertEquals(2, courseDao.findByNameContains(courseName).size()); // 2 courses should be returned back inside the course list
    }

    @Test
    @DisplayName("Returns list of courses that starts before date")
    void searchByDateBefore() {
        CreateCourseForm python = new CreateCourseForm(2, "Python, from zero to hero", LocalDate.of(2023, 9, 1), 25);
        LocalDate date = LocalDate.of(2023, 8, 29);

        testObject.create(python);
        assertEquals(course.getCourseName(), testObject.searchByDateBefore(date).get(0).getCourseName());
    }

    @Test
    @DisplayName("Returns list of Course objects that starts before date")
    void searchByDateAfter() {
        CreateCourseForm python = new CreateCourseForm(2, "Python, from zero to hero", LocalDate.of(2023, 9, 1), 25);
        LocalDate date = LocalDate.of(2023, 8, 29);

        testObject.create(python);
        assertEquals(python.getCourseName(), testObject.searchByDateAfter(date).get(0).getCourseName());
    }

    @Test
    @DisplayName("Returns Course object that has the same id")
    void findById() {
        assertEquals(course.getCourseName(), testObject.findById(1).getCourseName());
    }

    @Test
    @DisplayName("Returns list of all Course objects")
    void findAll() {
        assertEquals(1, testObject.findAll().size());
    }

    @Test
    @DisplayName("Returns list of Course objects that student has enrolled")
    void findByStudentId() {
        Student student = new Student(StudentSequencer.nextStudentId(), "Mikael Engvall", "mikael@engvall.org", "Vallgatan 22");
        course.enrollStudent(student);

        assertEquals(1,testObject.findByStudentId(student.getId()).size());
    }

    @Test
    @DisplayName("Deletes a course")
    void deleteCourse() {
        testObject.deleteCourse(1);
        assertEquals(0, testObject.findAll().size());
    }

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {

        assertNotNull(testObject);
        assertNotNull(courseDao);
    }

    //Write your tests here

    @AfterEach
    void tearDown() {
        courseDao.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
