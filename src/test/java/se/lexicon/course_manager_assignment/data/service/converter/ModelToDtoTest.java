package se.lexicon.course_manager_assignment.data.service.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;

    @Test
    @DisplayName("Should return a StudentView object from a Student object")
    StudentView studentToStudentView() {
        StudentCollectionRepository repo = new StudentCollectionRepository(new HashSet<Student>());
        Student studentMikael = repo.createStudent("Mikael Engvall", "mikael@engvall.se", "Vallgatan 22");

        StudentView studentView = new StudentView(studentMikael.getId(), studentMikael.getName(), studentMikael.getEmail(), studentMikael.getAddress());

        assertEquals(studentMikael.toString(), studentView.toString());
    } // TODO: I don't understand this...

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {

        assertNotNull(testObject);
    }


    //write your tests here
}
