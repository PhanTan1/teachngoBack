package be.teachngo.repository;

import be.teachngo.data.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository <BankAccount, Long> {
}
