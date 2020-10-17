package be.teachngo.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private String date;

    @Transient
    private String startTime;

    @Transient
    private String endTime;

    private double price;

    @Column(nullable = false, length = 10)
    private String status;

    @OneToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @OneToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @OneToOne
    @JoinColumn(name = "Id_mode")
    private Mode mode;

    @OneToOne
    @JoinColumn(name = "id_adresse")
    private Address address;


}
