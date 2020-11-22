package be.teachngo.repository;

import be.teachngo.data.Address;
import be.teachngo.data.Country;
import be.teachngo.data.Reservation;
import be.teachngo.data.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
class ReservationRepositoryTest {

    @Autowired
    private StudentRepository studentService;
    @Autowired
    private ReservationRepository reservationRepository;

    public static Student createStudent(String login) {
        Student student = new Student();
        student.setEmail("student@teachngo.com");
        student.setFirstName("Pierre");
        student.setLastName("Khi");
        student.setGender("Male");
        student.setPassword("password");
        student.setLogin(login);
        student.setPhone("+324852245");
        Address address = new Address();
        address.setBoxNumber("13");
        address.setCountry(Country.BELGIUM);
        address.setPostalCode("1070");
        address.setStreet("Avenue 2ç222éééé");
        address.setStreetNumber("284 A");
        student.setAdress(address);
        return student;
    }

    @Test
    void findByStudent() {
        studentService.deleteAll();
        Student student_11 = studentService.save(createStudent("student_11"));
        List<Reservation> byStudent = reservationRepository.findByStudent(student_11);
        assertEquals(0, byStudent.size());


    }
}