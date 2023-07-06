package mk.ukim.finki.wp.app.repository.jpa;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String courseName);
    List<Course> findCoursesByStudents(Student s);
}
