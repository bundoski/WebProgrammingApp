package mk.ukim.finki.wp.app.web.controller;

import mk.ukim.finki.wp.app.model.Course;
import mk.ukim.finki.wp.app.model.Grade;
import mk.ukim.finki.wp.app.model.Student;
import mk.ukim.finki.wp.app.model.exception.CourseNotFoundException;
import mk.ukim.finki.wp.app.model.exception.StudentNotFoundException;
import mk.ukim.finki.wp.app.service.CourseService;
import mk.ukim.finki.wp.app.service.GradeService;
import mk.ukim.finki.wp.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/grades")
public class GradeController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final GradeService gradeService;

    @Autowired
    public GradeController(CourseService courseService, StudentService studentService, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping("/add-grade/{id}")
    public String getAddGradePage(@PathVariable String id, HttpServletRequest req, Model model) {

        Long courseId = (Long) req.getSession().getAttribute("courseId");

        Course c;
        Student s;

        if (this.courseService.findById(courseId).isPresent()) {
            c = this.courseService.findById(courseId).get();
        } else {
            throw new CourseNotFoundException();
        }

        if (this.studentService.findByUsername(id).isPresent()) {
            s = this.studentService.findByUsername(id).get();
        } else {
            throw new StudentNotFoundException();
        }

        model.addAttribute("student", s);
        model.addAttribute("grade", this.gradeService.findGradeByCourseAndStudent(c, s));

        return "add-grade";
    }

    @PostMapping("/add")
    public String addGradeForStudent(@RequestParam String id,
                                     @RequestParam Character grade,
                                     @RequestParam
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                     HttpServletRequest req) {

        Long courseId = (Long) req.getSession().getAttribute("courseId");

        if (this.courseService.findById(courseId).isPresent() && this.studentService.findByUsername(id).isPresent()) {
            this.gradeService.saveGrade
                    (
                            grade,
                            this.courseService.findById(courseId).get(),
                            this.studentService.findByUsername(id).get(),
                            dateTime
                    );
        } else {
            throw new IllegalStateException("Course or student not found while trying to add a grade.");
        }

        return "redirect:/courses/listStudents?id=" + courseId;
    }

    @PostMapping("/filter")
    public String filterGrades(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                               HttpServletRequest req,
                               Model model) {
        Long courseId = (Long) req.getSession().getAttribute("courseId");

        if (fromDate == null || toDate == null) {
            return "redirect:/courses/listStudents?id=" + courseId;
        }

        List<Grade> filteredGrades = this.gradeService.filterForCourseByDate(courseId, fromDate, toDate);

        model.addAttribute("course", this.courseService.findById(courseId).get());
        model.addAttribute("students_in_course", this.studentService.findStudentsByGrades(filteredGrades));

        return "listStudentsInCourse";
    }

}
