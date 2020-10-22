package be.teachngo.data;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(length = 30, nullable = false, unique= true) // Chaque utilisateur aura un « login » unique
    private  String login;

    @Column(nullable = false)
    private String password;

    @Column(name = "firstname",length = 60, nullable = false)
    private  String firstName;

    @Column(name = "lastname",length = 60, nullable = false)
    private  String lastName;

    @Column(length = 20)
    private String gender;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 12, nullable = false)
    private String phone;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", nullable = false)
    private Address adress;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
}
