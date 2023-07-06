package mk.ukim.finki.wp.app.service.impl;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Grade;
import mk.ukim.finki.wp.app.model.Student;
import mk.ukim.finki.wp.app.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.app.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.app.service.GradeService;
import mk.ukim.finki.wp.app.model.exception.CourseNotFoundException;
import mk.ukim.finki.wp.app.repository.jpa.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Grade findGradeByCourseAndStudent(Course c, Student s) {
        return this.gradeRepository.findGradeByCourseAndStudent(c, s);
    }

    @Override
    public Grade saveGrade(Character grade, Course c, Student s, LocalDateTime dateTime) {
        Grade g = findGradeByCourseAndStudent(c, s);

        if (g != null) {
            g.setGrade(grade);
            g.setTimestamp(dateTime);
            return this.gradeRepository.save(g);
        } else {
            return this.gradeRepository.save(new Grade(grade, c, s, dateTime));
        }
    }

    @Override
    public List<Grade> findAllByCourse(Long courseId) {
        Course c;

        if (this.courseRepository.findById(courseId).isPresent()) {
            c = this.courseRepository.findById(courseId).get();
        } else {
            throw new CourseNotFoundException();
        }

        return this.gradeRepository.findAllByCourse(c);
    }

    @Override
    public List<Grade> filterForCourseByDate(Long courseId, LocalDateTime from, LocalDateTime to) {
        List<Grade> allGrades = this.gradeRepository.findAllByTimestampBetween(from, to);
        return allGrades.stream().filter(i -> i.getCourse().getCourseId().equals(courseId)).collect(Collectors.toList());
    }
}
