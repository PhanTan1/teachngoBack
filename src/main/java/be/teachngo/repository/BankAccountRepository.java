package be.teachngo.repository;

import be.teachngo.data.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository <BankAccount, Long> {
}
