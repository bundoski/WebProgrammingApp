package mk.ukim.finki.wp.app.web.controller;

import mk.ukim.finki.wp.app.model.Type;
import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Student;
import mk.ukim.finki.wp.app.service.CourseService;
import mk.ukim.finki.wp.app.service.StudentService;
import mk.ukim.finki.wp.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    @Autowired
    public CourseController(StudentService studentService, CourseService courseService, TeacherService teacherRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.teacherService = teacherRepository;
    }

    @GetMapping()
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", error);
        }

        model.addAttribute("list_of_courses", this.courseService.listAll());
        return "listCourses";
    }

    //    @PostMapping()
//    public String navigateToNextPage(HttpServletRequest req, Model model) {
//        Long courseId;
//
//        try {
//            courseId = Long.parseLong(req.getParameter("courseId"));
//            req.getSession().setAttribute("courseId", courseId);
//            return "redirect:/AddStudent";
//        } catch (NumberFormatException e) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("errorMessage", "Please select a course");
//            model.addAttribute("list_of_courses", this.courseService.listAll());
//            return "listCourses";
//        }
//    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddCoursePage(@RequestParam(required = false) String errorMessage, Model model) {

        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", errorMessage);
        }

        model.addAttribute("list_of_teachers", this.teacherService.findAll());
        model.addAttribute("courseType", Type.values());
        return "add-course";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCourse(@RequestParam(required = false) Long courseId,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long teacherId,
                             @RequestParam Type type) {
        try {
            this.courseService.saveCourse(courseId, name, description, teacherId, type);
            return "redirect:/courses";
        } catch (RuntimeException e) {
            if (courseId != null) {
                String test = "/courses/edit-form/" + courseId + "?errorMessage=CourseExists";
                return "redirect:" + test;
            } else {
                return "redirect:/courses/add-form?errorMessage=CourseExists";
            }
        }
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditCoursePage(@PathVariable Long id, Model model) {

        if (this.courseService.findById(id).isPresent()) {
            Course course = this.courseService.findById(id).get();
            model.addAttribute("courseObject", course);
            model.addAttribute("list_of_teachers", this.teacherService.findAll());
            model.addAttribute("courseType", Type.values());
            return "add-course";
        }

        return "redirect:/courses?error=CourseNotFound";
    }

    // Delete mi e mapirano so GET sto e loso
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }


    @GetMapping("/listStudents")
    public String showStudentsForCourse(@RequestParam Long id, HttpServletRequest request, Model model) {
        request.getSession().setAttribute("courseId", id);
        Course c = this.courseService.findById(id).get();
        List<Student> students = this.studentService.findStudentsByCourse(c);

        model.addAttribute("course", c);
        model.addAttribute("students_in_course", students);

        return "listStudentsInCourse";
    }

    @GetMapping("/listCourses")
    public String showCoursesForStudent(@RequestParam String id, Model model) {
        Student s = this.studentService.findByUsername(id).get();

        model.addAttribute("student", s);
        model.addAttribute("list_of_courses", this.courseService.findCoursesForStudent(s));

        return "listCoursesForStudent";
    }

    @GetMapping("/add-new-student-page")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddNewStudentToCoursePage(@RequestParam Long courseId, Model model) {
        model.addAttribute("course", this.courseService.findById(courseId).get());
        model.addAttribute("students", this.studentService.findAll());
        return "addStudentToCourse";
    }

    // i ova e loso sto mi e so GET, treba da e POST
    @GetMapping("/add-new-student")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewStudentToCourse(@RequestParam String username, @RequestParam Long courseId) {
        this.courseService.addStudentInCourse(username, courseId);
        return "redirect:/courses/listStudents?id=" + courseId;
    }

}
