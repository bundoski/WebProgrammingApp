package mk.ukim.finki.wp.app.service;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Student;
import mk.ukim.finki.wp.app.model.Type;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> listAll();
    List<Course> findCoursesForStudent(Student s);
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    Optional<Course> findById(Long courseId);
    Optional<Course> findByName(String name);
    Course saveCourse(Long courseId, String name, String description, Long teacherId, Type type);
    void deleteById(Long courseId);
}
