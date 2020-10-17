package be.teachngo.repository;

import be.teachngo.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long > {
}
