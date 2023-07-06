//package mk.ukim.finki.wp.lab1_b.repository.impl;
//
//import mk.ukim.finki.wp.lab1_b.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab1_b.model.Student;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class StudentRepository {
//
//    public List<Student> findAllStudents() {
//        return DataHolder.studentList;
//    }
//
//    public Optional<Student> findByUsername(String username) {
//        return DataHolder.studentList.stream().filter(i -> i.getUsername().equals(username)).findFirst();
//    }
//
//    public List<Student> findAllByNameOrSurname(String text) {
//        return DataHolder.studentList
//                .stream()
//                .filter(s -> s.getName().equals(text) || s.getSurname().equals(text))
//                .collect(Collectors.toList());
//    }
//
//    public Student saveOrUpdate(Student student) {
//        DataHolder.studentList.removeIf(i -> i.getUsername().equals(student.getUsername()));
//        DataHolder.studentList.add(student);
//        return student;
//    }
//
//    public List<Student> search(String text) {
//        return DataHolder.studentList
//                .stream()
//                .filter(i -> i.getName().contains(text) || i.getSurname().contains(text))
//                .collect(Collectors.toList());
//    }
//}
