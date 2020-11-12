package be.teachngo.repository;

import be.teachngo.data.Country;
import be.teachngo.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

    List<Student> findByAdressPostalCode(String postalCode);

    List<Student> findByAdressCountry(Country country);
}
