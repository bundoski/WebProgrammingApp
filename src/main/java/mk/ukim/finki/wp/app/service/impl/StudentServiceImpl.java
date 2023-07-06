package mk.ukim.finki.wp.app.service.impl;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Grade;
import mk.ukim.finki.wp.app.model.Student;
import mk.ukim.finki.wp.app.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.app.service.StudentService;
import mk.ukim.finki.wp.app.model.exception.StudentNotFoundException;
import mk.ukim.finki.wp.app.repository.jpa.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return this.studentRepository.findAllByNameOrSurname(text, text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        return this.studentRepository.save(new Student(username, password, name, surname));
    }

    @Override
    public List<Student> search(String text) {
        return this.studentRepository.findAllByNameOrSurnameLike(text, text);
    }

    @Override
    public Optional<Student> findByUsername(String username) {
        if (this.studentRepository.findByUsername(username).isEmpty()) {
            throw new StudentNotFoundException();
        }

        return this.studentRepository.findByUsername(username);
    }

    @Override
    public List<Student> findStudentsByCourse(Course c) {
        return this.studentRepository.findAllByCourses(c);
    }

    @Override
    public List<Course> getStudentsCourses(Student s) {
        return this.courseRepository.findCoursesByStudents(s);
    }

    @Override
    public List<Student> findStudentsByGrades(List<Grade> grades) {
        return this.studentRepository.findStudentsByGradesIn(grades);
    }

}
