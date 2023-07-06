package mk.ukim.finki.wp.app.service.impl;

import mk.ukim.finki.wp.app.model.Teacher;
import mk.ukim.finki.wp.app.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return this.teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.teacherRepository.deleteById(id);
    }

    @Override
    public Teacher save(Long id, String name, String surname, LocalDate date) {
//        if (id != null && this.teacherRepository.findById(id).isPresent()) {
//            this.teacherRepository.deleteById(id);
//        }

        return this.teacherRepository.save(new Teacher(name, surname, date));
    }

}
