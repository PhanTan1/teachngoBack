package be.teachngo.repository;

import be.teachngo.data.Reservation;
import be.teachngo.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStudent(Student student);

    List<Reservation> findByStudentId(Long id);
}
