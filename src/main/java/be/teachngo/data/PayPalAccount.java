package be.teachngo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class PayPalAccount extends Account{

    @Column(nullable = false, length = 100)
    private String email;
}
