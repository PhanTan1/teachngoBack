package be.teachngo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "Modes")
public class Mode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mode_id")
    private Long modeId;

    @Column(name = "mode_name", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private ModeType modeName;



}
