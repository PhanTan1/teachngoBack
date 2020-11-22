package be.teachngo.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Payements")
public class Payement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;


    @Column(length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private PayementStatus status;

    @Column(length = 45, nullable = false)
    private Date date;
    @Column(length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMode mode;
    @Column(length = 45)
    private String message;
    @Column(length = 45)
    private String structure;
    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private Account sender;

    @Transient
    private Long senderAccountId;

    @ManyToOne
    @JoinColumn(name = "reciever", nullable = false)
    private Account reciever;
}
