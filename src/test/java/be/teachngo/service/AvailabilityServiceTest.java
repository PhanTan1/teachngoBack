package be.teachngo.service;

import be.teachngo.data.*;
import be.teachngo.repository.TeacherCourseRepository;
import be.teachngo.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static be.teachngo.service.TeacherServiceTest.createTeacher;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
//@ActiveProfiles("test")
public class AvailabilityServiceTest {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Test
    public void testNewAvailability() {
        Availability availability = new Availability();
        availability.setStartTime(DateUtils.stringToDate("31/12/1998 21:37"));
        availability.setEndTime(DateUtils.stringToDate("31/12/1998 23:37"));
        Teacher teacher = teacherService.save(createTeacher("teacher_1"));
        Course course = new Course();
        course.setDescription("Course Math");
        course.setCategory(Category.LANGUE);
        course.setSize(50);
        course.setSubject("Math");
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourse(course);
        teacherCourse.setTeacher(teacher);
        teacherCourse.setPrice(25.0f);
        availability.setTeacherCourse(teacherCourse);
        teacherCourse = teacherCourseRepository.save(teacherCourse);
        availability.setTeacherCourse(teacherCourse);
        availabilityService.save(availability);
        List<Availability> availabilitiesOfTeacher = availabilityService.findAvailabilitiesOfTeacher(teacher);
        assertEquals(1, availabilitiesOfTeacher.size());
    }

    @Test
    public void testNewAvailability_RealDatabse() {
        Teacher teacher = teacherService.getTeacher(1L);
        Availability availability = new Availability();
        availability.setStartTime(DateUtils.stringToDate("31/12/2020 21:37"));
        availability.setEndTime(DateUtils.stringToDate("31/12/2020 23:37"));
        Course course = new Course();
        course.setDescription("Course Mathematic");
        course.setCategory(Category.PRIMAIRE);
        course.setSize(50);
        course.setSubject("Mathematic");
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourse(course);
        teacherCourse.setTeacher(teacher);
        teacherCourse.setPrice(25.0f);
        availability.setTeacherCourse(teacherCourse);
        teacherCourse = teacherCourseRepository.save(teacherCourse);
        availability.setTeacherCourse(teacherCourse);
        availabilityService.save(availability);
        List<Availability> availabilitiesOfTeacher = availabilityService.findAvailabilitiesOfTeacher(teacher);
        assertEquals(1, availabilitiesOfTeacher.size());
    }


}
