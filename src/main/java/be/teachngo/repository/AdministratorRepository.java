package be.teachngo.repository;

import be.teachngo.data.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findByLogin(String login);
}
