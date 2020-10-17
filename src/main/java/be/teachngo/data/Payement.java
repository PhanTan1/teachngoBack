package be.teachngo.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Payements")
public class Payement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @Column(length = 45, nullable = false)
    private String status;

    private String date;
    @Column(length = 45, nullable = false)
    private String mode;
    @Column(length = 45)
    private String message;
    @Column(length = 45)
    private String structure;
    @Column(nullable = false)
    private int from;
    @Column(nullable = false)
    private int to;
}
