package be.teachngo.repository;

import be.teachngo.data.Account;
import be.teachngo.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long > {


    List<Account> findByUser(User user);

    List<Account> findByUserId(Long id);
}
