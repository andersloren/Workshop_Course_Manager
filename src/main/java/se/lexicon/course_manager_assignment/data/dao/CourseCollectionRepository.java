package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        Course course = new Course(CourseSequencer.nextCourseId(), courseName, startDate, weekDuration);
        this.courses.add(course);
        return course;
    }

    @Override
    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == (id)) {
                return course;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        ArrayList<Course> coursesFound = new ArrayList<>();
        for (Course course : this.courses) {
            if (course.getCourseName().toLowerCase().contains(name.toLowerCase())) { // Ensures that we find all courses containing 'name'
                coursesFound.add(course);
            }
        }
        return coursesFound;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        HashSet<Course> coursesBeforeDate = new HashSet<>();
        for (Course course : this.courses) {
            if (course.getStartDate().isBefore(end)) {
                coursesBeforeDate.add(course);
            }
        }
        return coursesBeforeDate;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        HashSet<Course> coursesAfterDate = new HashSet<>();
        for (Course course : this.courses) {
            if (course.getStartDate().isAfter(start)) {
                coursesAfterDate.add(course);
            }
        }
        return coursesAfterDate;
    }

    @Override
    public Collection<Course> findAll() {
        return this.courses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        HashSet<Course> coursesByStudentId = new HashSet<>(); // HashSet for the students (if any) that will be returned
        for (Course course : courses) { // Check each course to extract students
            HashSet<Student> students = new HashSet<>(course.getStudents()); // Extract students to match them with argument
            for (Student student : students) {
                if (student.getId() == studentId) { // If argument matches any student Id, the student will be added to the HashSet
                    coursesByStudentId.add(course);
                }
            }
        }
        return coursesByStudentId;
    }

    @Override
    public boolean removeCourse(Course course) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course nextCourse = iterator.next();
            if (nextCourse.equals(course)) {
                iterator.remove();
                return true;
            }

        }
        return false;
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}
