package be.teachngo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Account")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter

public abstract class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(length = 45, nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


}
