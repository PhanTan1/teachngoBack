package be.teachngo.repository;

import be.teachngo.data.Payement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayementRepository extends JpaRepository<Payement, Long> {
}
