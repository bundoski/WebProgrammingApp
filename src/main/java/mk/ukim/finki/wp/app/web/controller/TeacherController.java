package mk.ukim.finki.wp.app.web.controller;

import mk.ukim.finki.wp.app.model.Teacher;
import mk.ukim.finki.wp.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public String getTeachersPage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", error);
        }

        model.addAttribute("teachers", this.teacherService.findAll());
        return "listTeachers";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddTeachersForm() {
        return "add-teacher";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addTeacher(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        this.teacherService.save(id, name, surname, date.toLocalDate());
        return "redirect:/teachers";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditTeacherForm(@PathVariable Long id, Model model) {

        if (this.teacherService.findById(id).isPresent()) {
            Teacher teacher = this.teacherService.findById(id).get();
            model.addAttribute("teacher", teacher);
            return "add-teacher";
        }

        return "redirect:/teachers?error=TeacherNotFound";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteTeacher(@PathVariable Long id) {
        this.teacherService.deleteById(id);
        return "redirect:/teachers";
    }

}
