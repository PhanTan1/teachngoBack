package be.teachngo.data;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 60, nullable = false)
    private String subject;
    @Column(nullable = false)
    private String description;
    @Column
    private int size;
    @Column(length = 45, nullable = false)
    private String level;

    @OneToMany
    private List<TeacherCourse> teacherCourses;
}
