package be.teachngo.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collection;

@Entity(name="Teachers")
@Getter
@Setter
public class Teacher extends User{


    @Column(columnDefinition = "TEXT", nullable = false)
    private String biography;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(Role.TEACHER.getRole()));
    }
}
