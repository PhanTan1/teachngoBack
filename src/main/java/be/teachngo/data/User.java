package be.teachngo.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 5155720064139820502L;

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

    @JsonIgnore
    private String token;

    private boolean active;

    @JsonIgnore
    @Override
    public String getUsername() {
        return login;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
