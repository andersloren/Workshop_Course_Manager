package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ModelToDto implements Converters {
    @Override
    public StudentView studentToStudentView(Student student) {
        if (student != null) {
            return new StudentView(student.getId(), student.getName(), student.getEmail(), student.getAddress());
        }
        return null; // TODO: This causes an error message, fix this
    }

    @Override
    public CourseView courseToCourseView(Course course) {
        return new CourseView(course.getId(), course.getCourseName(), course.getStartDate(), course.getWeekDuration(), studentsToStudentViews(course.getStudents()));
    }

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        List<CourseView> coursesView = new ArrayList<>();
        for (Course course : courses) {
            coursesView.add(courseToCourseView(course));
        }
        return coursesView;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        List<StudentView> studentsView = new ArrayList<>();
        for (Student student : students) {
            studentsView.add(studentToStudentView(student));
        }
        return studentsView;
    }
}
