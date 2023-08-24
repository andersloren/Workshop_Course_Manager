package se.lexicon.course_manager_assignment.model;

import java.util.Objects;
import java.io.Serializable;

public class Student implements Serializable{

    // Fields
    private Integer id;
    private String name;
    private String email;
    private String address;

    //Constructor(s)

    public Student() {
    }
    public Student(Integer id) {
        this.id = id;
    }
    public Student(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
    public Student(Integer id, String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
