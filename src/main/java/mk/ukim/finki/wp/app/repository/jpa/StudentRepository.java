package mk.ukim.finki.wp.app.repository.jpa;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Grade;
import mk.ukim.finki.wp.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByUsername(String username);
    List<Student> findAllByNameOrSurname(String name, String surname);
    List<Student> findAllByNameOrSurnameLike(String name, String surname);
    List<Student> findAllByCourses(Course c);
    List<Student> findStudentsByGradesIn(List<Grade> grades);
}
