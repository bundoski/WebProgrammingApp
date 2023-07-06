package mk.ukim.finki.wp.app.service;

import mk.ukim.finki.wp.app.model.Teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Optional<Teacher> findById(Long id);
    List<Teacher> findAll();
    void deleteById(Long id);
    Teacher save(Long id, String name, String surname, LocalDate date);
}
