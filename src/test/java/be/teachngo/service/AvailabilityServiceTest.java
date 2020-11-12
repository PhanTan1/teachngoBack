package be.teachngo.service;

import be.teachngo.data.Availability;
import be.teachngo.data.Teacher;
import be.teachngo.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static be.teachngo.service.TeacherServiceTest.createTeacher;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AvailabilityServiceTest {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private TeacherService teacherService;

    @Test
    public void testNewAvailability() {
        Teacher teacher = teacherService.save(createTeacher("teacher_1"));
        Availability availability = new Availability();
        availability.setStartTime(DateUtils.stringToDate("31/12/1998 21:37"));
        availability.setEndTime(DateUtils.stringToDate("31/12/1998 23:37"));
        availability.setTeacher(teacher);
        availabilityService.save(availability);
        List<Availability> availabilitiesOfTeacher = availabilityService.findAvailabilitiesOfTeacher(teacher);
        assertEquals(1, availabilitiesOfTeacher.size());
    }

}
