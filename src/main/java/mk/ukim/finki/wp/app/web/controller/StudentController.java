package mk.ukim.finki.wp.app.web.controller;

import mk.ukim.finki.wp.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String showAllStudents(Model model) {
        model.addAttribute("students", this.studentService.findAll());

        return "listStudents";
    }

    @GetMapping("/add-form")
    public String getCreateStudentPage() {
        return "add-student";
    }

    @PostMapping("/add")
    public String addNewStudent(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        this.studentService.save(username, password, name, surname);

        return "redirect:/students";
    }

    // nemam logika za brisenje i editiranje student
}
