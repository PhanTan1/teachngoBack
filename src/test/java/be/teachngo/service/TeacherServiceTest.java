package be.teachngo.service;

import be.teachngo.data.Address;
import be.teachngo.data.Country;
import be.teachngo.data.Teacher;
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
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;


    @Test
    void getAllTeachersAvailableOn() {
        teacherService.save(createTeacher("teacher_1"));
        teacherService.save(createTeacher("teacher_2"));
        Teacher teacher2 = createTeacher("teacher_3");
        teacher2.getAdress().setPostalCode("1080");
        teacherService.save(teacher2);
        assertEquals(2, teacherService.getAllTeachersAvailableOn("1070").size());
    }

    @Test
    void getAllTeachersAvailableOnCountry() {
        teacherService.removeAll();
        teacherService.save(createTeacher("teacher_11"));
        teacherService.save(createTeacher("teacher_12"));
        Teacher teacher3 = createTeacher("teacher_13");
        teacher3.getAdress().setPostalCode("1080");
        teacherService.save(teacher3);
        assertEquals(3, teacherService.getAllTeachersAvailableOn(Country.BELGIUM).size());
    }

    private Teacher createTeacher(String login) {
        Teacher teacher = new Teacher();
        teacher.setEmail("teacher@teachngo.com");
        teacher.setFirstName("Admin");
        teacher.setLastName("_");
        teacher.setGender("NP");
        teacher.setPassword("Password");
        teacher.setLogin(login);
        teacher.setPhone("+32485055204");
        Address address = new Address();
        address.setBoxNumber("12");
        address.setCountry(Country.BELGIUM);
        address.setPostalCode("1070");
        address.setStreet("Borgtstraat");
        address.setStreetNumber("12A");
        teacher.setAdress(address);
        return teacher;
    }
}