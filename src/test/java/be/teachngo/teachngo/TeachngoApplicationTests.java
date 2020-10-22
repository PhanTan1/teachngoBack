package be.teachngo.teachngo;

import be.teachngo.data.Teacher;
import be.teachngo.repository.TeacherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class TeachngoApplicationTests {

    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    public void testSaveTeacherWithAdress() {
        Teacher teacher = new Teacher();
        teacher.setEmail("teacher@teachngo.com");
        teacher.setFirstName("Admin");
        teacher.setLastName("_");
        teacher.setGender("NP");
        teacher.setPassword("Password");
        teacher.setLogin("teacher");
        teacher.setPhone("+32485055204");
        teacherRepository.save(teacher);
    }
}
