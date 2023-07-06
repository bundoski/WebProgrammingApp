package mk.ukim.finki.wp.app.service.impl;


import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Student;
import mk.ukim.finki.wp.app.model.Teacher;
import mk.ukim.finki.wp.app.model.Type;
import mk.ukim.finki.wp.app.model.exception.CourseAlreadyExistsException;
import mk.ukim.finki.wp.app.model.exception.CourseNotFoundException;
import mk.ukim.finki.wp.app.model.exception.StudentNotFoundException;
import mk.ukim.finki.wp.app.model.exception.TeacherNotFoundException;
import mk.ukim.finki.wp.app.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.app.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.app.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Course> listAll() {
        return this.courseRepository.findAll().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
    }

    @Override
    public List<Course> findCoursesForStudent(Student s) {
        return this.courseRepository.findCoursesByStudents(s);
    }

    @Override
    public Optional<Course> findById(Long courseId) {
        if (this.courseRepository.findById(courseId).isEmpty()) {
            throw new CourseNotFoundException();
        }
        return this.courseRepository.findById(courseId);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return this.courseRepository.findByName(name);
    }

    // TODO baranje od labs sho gi praev, sredi go posle
//    @Override
//    public List<Course> getStudentsCourses(Student s) {
//        return this.courseRepository.findAll();
//    }

    @Override
    public Course saveCourse(Long courseId, String name, String description, Long teacherId, Type type) {

        Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(teacherId));

        if (courseId != null && findById(courseId).isPresent()) {
            Course c = this.courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);
            c.setName(name);
            c.setDescription(description);
            c.setTeacher(teacher);
            c.setType(type);
            return courseRepository.save(c);
        } else if (courseId != null && findByName(name).isPresent() && findById(courseId).isEmpty()) {
            throw new CourseAlreadyExistsException(name);
        }

        return this.courseRepository.save(new Course(name, description, teacher, type));
    }

    @Override
    public void deleteById(Long courseId) {
        this.courseRepository.deleteById(courseId);
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
//        Course course = this.courseRepository.findById(courseId).orElseThrow(null);
//        return course.getStudents();
        Course c = this.courseRepository.findById(courseId).get();
        return this.studentRepository.findAllByCourses(c);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {

        if (this.studentRepository.findByUsername(username).isEmpty()) {
            throw new StudentNotFoundException();
        }

        if (this.courseRepository.findById(courseId).isEmpty()) {
            throw new CourseNotFoundException();
        }

        Student s = this.studentRepository.findByUsername(username).get();
        Course c = this.courseRepository.findById(courseId).get();

        if (c.getStudents().stream().filter(i -> i.getUsername().equals(s.getUsername())).findFirst().isEmpty()) {
            c.getStudents().add(s);
            return this.courseRepository.save(c);
        } else {
            return c;
        }

//        if (!c.getStudents().contains(s)){
//            c.getStudents().add(s);
//            return this.courseRepository.save(c);
//        } else {
//            return c;
//        }
    }
}
