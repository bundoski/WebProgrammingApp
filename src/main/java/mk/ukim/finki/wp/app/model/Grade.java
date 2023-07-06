package mk.ukim.finki.wp.app.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Character grade;

    @ManyToOne
    @JoinColumn//(name = "fk_course")
    private Course course;

    @ManyToOne
    @JoinColumn//(name = "fk_student")
    private Student student;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;

    public Grade(Character grade, Course course, Student student, LocalDateTime timestamp) {
        this.grade = grade;
        this.course = course;
        this.student = student;
        this.timestamp = timestamp;
    }
}
