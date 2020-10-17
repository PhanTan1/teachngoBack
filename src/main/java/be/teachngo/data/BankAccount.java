package be.teachngo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class BankAccount extends Account{

    @Column(nullable = false, length = 45)
    private String number;

    @Column(nullable = false, length = 34, name="IBAN")
    private String iban;

    @Column(length = 8, name="BIC")
    private String bic;
}
