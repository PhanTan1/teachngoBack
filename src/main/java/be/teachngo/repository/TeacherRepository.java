package be.teachngo.repository;

import be.teachngo.data.Country;
import be.teachngo.data.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByAdressPostalCode(String postalCode);

    List<Teacher> findByAdressCountry(Country country);
}
