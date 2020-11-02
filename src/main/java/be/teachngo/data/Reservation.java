package be.teachngo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String date;

    @Transient
    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @OneToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "id_course", nullable = false)
    private TeacherCourse course;

    @OneToOne
    @JoinColumn(name = "Id_mode", nullable = false)
    private Mode mode;

    @OneToOne
    @JoinColumn(name = "id_adresse")
    private Address address;


}
