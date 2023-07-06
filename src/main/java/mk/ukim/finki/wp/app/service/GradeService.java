package mk.ukim.finki.wp.app.service;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Grade;
import mk.ukim.finki.wp.app.model.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface GradeService {
    Grade findGradeByCourseAndStudent(Course c, Student s);
    Grade saveGrade(Character grade, Course c, Student s, LocalDateTime dateTime);
    List<Grade> findAllByCourse(Long courseId);
    List<Grade> filterForCourseByDate(Long courseId, LocalDateTime from, LocalDateTime to);
}
