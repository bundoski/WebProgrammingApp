//package mk.ukim.finki.wp.lab1_b.repository.impl;
//
//import mk.ukim.finki.wp.lab1_b.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab1_b.model.Course;
//import mk.ukim.finki.wp.lab1_b.model.Teacher;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class TeacherRepository {
//
//    public List<Teacher> findAll() {
//        return DataHolder.teacherList;
//    }
//
//    public Optional<Teacher> findById(Long teacherId) {
//        return DataHolder.teacherList
//                .stream()
//                .filter(i -> i.getId().equals(teacherId))
//                .findFirst();
//    }
//
//    public void deleteById(Long courseId) {
//        DataHolder.teacherList.removeIf(i -> i.getId().equals(courseId));
//    }
//
//    public Teacher saveOrUpdate(Teacher teacher, Long id) {
//        if (id != null) {
//            DataHolder.teacherList.removeIf(i -> i.getId().equals(id));
//        }
//        DataHolder.teacherList.add(teacher);
//        return  teacher;
//    }
//}
