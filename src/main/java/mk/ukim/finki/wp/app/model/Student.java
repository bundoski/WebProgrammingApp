package mk.ukim.finki.wp.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;

    // beshe eager loaded
    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Character getGradeForCourse(Course c) {
        Character ch = this.grades.stream()
                .filter(i -> i.getCourse().getCourseId().equals(c.getCourseId()) && i.getStudent().getUsername().equals(this.username))
                .findFirst().orElse(new Grade()).getGrade();
        return ch;
    }

}
