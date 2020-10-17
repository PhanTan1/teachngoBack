package be.teachngo.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Modes")
public class Mode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mode_id")
    private Long modeId;

    @Column(name = "mode_name", length = 10, nullable = false)
    private String modeName;
    @Column(name = "Teachers_idTeachers", length = 11, nullable = false)
    private Integer teachersIdTeachers;


}
