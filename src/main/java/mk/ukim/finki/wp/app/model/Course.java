package mk.ukim.finki.wp.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_teacher")
    private Teacher teacher;

    // beshe fetch = FetchType.EAGER,
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "course_student",
            joinColumns = {@JoinColumn(name = "fk_course")},
            inverseJoinColumns = {@JoinColumn(name = "fk_student")}
    )
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Grade> grades;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    public Course(String name, String description, Teacher teacher, Type type) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.type = type;
        this.students = new ArrayList<>();
    }
}
