package be.teachngo.service;

import be.teachngo.data.Address;
import be.teachngo.data.Country;
import be.teachngo.data.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void getAllStudentsAvailableOn() {
        studentService.removeAll();
        studentService.save(createStudent("student_1"));
        studentService.save(createStudent("student_2"));
        Student student2 = createStudent("student_3");
        student2.getAdress().setPostalCode("1080");
        studentService.save(student2);
        assertEquals(2, studentService.getAllStudentsAvailableOn("1070").size());
    }

    @Test
    void getAllStudentsAvailableOnCountry() {
        studentService.removeAll();
        studentService.save(createStudent("student_11"));
        studentService.save(createStudent("student_12"));
        Student student3 = createStudent("student_13");
        student3.getAdress().setPostalCode("1080");
        studentService.save(student3);
        assertEquals(3, studentService.getAllStudentsAvailableOn(Country.BELGIUM).size());
    }

    private Student createStudent(String login) {
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
}
