package mk.ukim.finki.wp.app.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    // ova pattern nesto ne raboti, pak mi gi zacuvuva vo baza kako yyyy-MM-dd
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfEmployment;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;


    public Teacher(String name, String surname, LocalDate dateOfEmployment) {
        this.name = name;
        this.surname = surname;
        this.dateOfEmployment = dateOfEmployment;
    }
}
