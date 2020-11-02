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
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    @Column(length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private PayementStatus status;

    @Column(length = 45, nullable = false)
    private String date;
    @Column(length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private PayementStatus mode;
    @Column(length = 45)
    private String message;
    @Column(length = 45)
    private String structure;
    @ManyToOne
    @JoinColumn(name = "from", nullable = false)
    private Account from;
    @ManyToOne
    @JoinColumn(name = "to", nullable = false)
    private Account to;
}
