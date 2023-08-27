package se.lexicon.course_manager_assignment.data.dao;

import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.*;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        Student student = new Student(StudentSequencer.nextStudentId(), name, email, address);
        this.students.add(student);
        return student;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {
        for (Student student : this.students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        ArrayList<Student> studentsFound = new ArrayList<>();
        for (Student student : this.students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                studentsFound.add(student);
            }
        }
        return studentsFound;
    }

    @Override
    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == (id)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findAll() {
        return this.students;
    }

    @Override
    public boolean removeStudent(Student student) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student nextStudent = iterator.next();
            if (nextStudent.equals(student)) {
                iterator.remove();
                return true;
            }

        }
        return false;
    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
    }

    @Override
    public String toString() {
        return "StudentCollectionRepository{" +
                "students=" + students +
                '}';
    }
}
