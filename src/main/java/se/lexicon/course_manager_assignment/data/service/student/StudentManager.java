package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired // Spring can create a bean and inject into this constructor, thanks to @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        Student student = studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress());
        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        Student student = studentDao.findById(form.getId());
        student.setName(form.getName());
        student.setEmail(form.getEmail());
        student.setAddress(form.getAddress());
        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView findById(int id) {
        Student student = studentDao.findById(id);
        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student student = studentDao.findByEmailIgnoreCase(email);
        return converters.studentToStudentView(student);
    }

    @Override
    public List<StudentView> searchByName(String name) {
        List<StudentView> studentFound = new ArrayList<>();
        for (StudentView student : converters.studentsToStudentViews(studentDao.findAll())) {
            if (name.equalsIgnoreCase(student.getName())) {
                studentFound.add(student);
            }
        }
        return studentFound;
    }

    @Override
    public List<StudentView> findAll() {
        return converters.studentsToStudentViews(studentDao.findAll());
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentDao.removeStudent(studentDao.findById(id));
    }
}
