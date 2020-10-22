package be.teachngo.repository;

import be.teachngo.data.PayPalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayPalAccountRepository extends JpaRepository <PayPalAccount, Long> {
}
