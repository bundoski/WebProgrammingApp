package mk.ukim.finki.wp.app.service;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Grade;
import mk.ukim.finki.wp.app.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    List<Student> searchByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);
    List<Student> search(String text);
    Optional<Student> findByUsername(String username);
    List<Student> findStudentsByCourse(Course c);
    List<Course> getStudentsCourses (Student s);
    List<Student> findStudentsByGrades(List<Grade> grades);
}
