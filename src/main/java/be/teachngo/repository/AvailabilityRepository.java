package be.teachngo.repository;

import be.teachngo.data.Availability;
import be.teachngo.data.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByTeacher(Teacher teacher);
}
