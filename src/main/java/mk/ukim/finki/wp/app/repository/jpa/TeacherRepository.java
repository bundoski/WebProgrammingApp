package mk.ukim.finki.wp.app.repository.jpa;

import mk.ukim.finki.wp.app.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
