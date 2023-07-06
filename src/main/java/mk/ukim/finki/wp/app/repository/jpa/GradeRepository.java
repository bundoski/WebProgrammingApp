package mk.ukim.finki.wp.app.repository.jpa;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Grade;
import mk.ukim.finki.wp.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findGradeByCourseAndStudent(Course c, Student s);
    List<Grade> findAllByCourse(Course c);
    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
