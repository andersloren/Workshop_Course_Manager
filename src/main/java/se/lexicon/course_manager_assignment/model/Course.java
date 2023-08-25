package se.lexicon.course_manager_assignment.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import java.io.Serializable;

public class Course implements Serializable{

    // Fields
    private Integer id;
    private String courseName;
    private LocalDate startDate;
    private Integer weekDuration;
    private Collection<Student> students;

    //Constructor(s)
    public Course() {
    }

    public Course(Integer id, String courseName, LocalDate startDate, Integer weekDuration) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = new HashSet<>();
    }

    //Methods
    public boolean enrollStudent(Student student) {
        if (student.getName() == null || student.getEmail() == null || student.getAddress() == null ) {
            students = new HashSet<>();
            return false;
        }
        for (Student element : students) {
            if (student.equals(element)) {
                return false;
            }
        }
        return this.students.add(student);
    }

    public boolean unrollStudent(Student student) {
        for (Student element : students) {
            if (student.equals(element)) {
                return this.students.remove(student);
            }
        }
        return false;
    }

    // Overridden methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && weekDuration == course.weekDuration && Objects.equals(courseName, course.courseName) && Objects.equals(startDate, course.startDate) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(Integer weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }
}
