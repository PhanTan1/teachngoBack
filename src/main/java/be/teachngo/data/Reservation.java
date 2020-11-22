package be.teachngo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Transient
    @Column(nullable = false)
    private Date date;


    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    // TODO ; remove the cascade because it is not possible that not peristed student can make a reservation
    @OneToOne(cascade = CascadeType.PERSIST)
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

    @OneToOne
    @JoinColumn(name = "id_availability", nullable = false)
    private Availability availability;


    @Transient
    private String ErrorMessage;


}
