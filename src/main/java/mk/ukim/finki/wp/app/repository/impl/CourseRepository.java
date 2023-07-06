//package mk.ukim.finki.wp.lab1_b.repository.impl;
//
//import mk.ukim.finki.wp.lab1_b.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab1_b.model.Course;
//import mk.ukim.finki.wp.lab1_b.model.Student;
//import mk.ukim.finki.wp.lab1_b.model.exception.CourseNotFoundException;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//import javax.xml.crypto.Data;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class CourseRepository {
//
//    public List<Course> findAllCourses() {
//        return DataHolder.courseList;
//    }
//
//    public Optional<Course> findById(Long courseId) {
//        return DataHolder.courseList.stream().filter(k -> k.getCourseId().equals(courseId)).findFirst();
//    }
//
//    public List<Student> findAllStudentsByCourse(Long courseId) {
//        if (findById(courseId).isEmpty()) {
//            throw new CourseNotFoundException();
//        }
//
//        return findById(courseId).get().getStudents();
//    }
//
//    public Optional<Course> findByName(String name) {
//        return DataHolder.courseList.stream().filter(i -> i.getName().equals(name)).findFirst();
//    }
//
//    public Course addStudentToCourse(Student student, Course course) {
//        findAllStudentsByCourse(course.getCourseId()).removeIf(i -> i.getUsername().equals(student.getUsername()));
//        course.getStudents().add(student);
//
//        return course;
//    }
//
//    public List<Course> getStudentsCourses(Student student) {
//        List<Course> list = new ArrayList<>();
//
//        for (Course c : DataHolder.courseList) {
//            for (Student s : c.getStudents()) {
//                if (s.equals(student)) {
//                    list.add(c);
//                }
//            }
//        }
//
//        return list;
//    }
//
//    public Course saveOrUpdate(Course course, Long courseId) {
//        if (courseId != null) {
//            DataHolder.courseList.removeIf(i -> i.getCourseId().equals(courseId));
//        }
//        DataHolder.courseList.add(course);
//        return  course;
//    }
//
//    public void deleteById(Long courseId) {
//        DataHolder.courseList.removeIf(i -> i.getCourseId().equals(courseId));
//    }
//}
