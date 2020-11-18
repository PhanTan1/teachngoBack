package be.teachngo.service;

import be.teachngo.data.*;
import be.teachngo.repository.ModeRepository;
import be.teachngo.repository.TeacherCourseRepository;
import be.teachngo.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static be.teachngo.service.TeacherServiceTest.createTeacher;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
class ReservationServiceImplTest {
    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private ModeRepository modeRepository;

    @Autowired
    private ReservationService service;

    @Test
    void makeAReservation() {

        // Build the availability
        Availability availability = new Availability();
        availability.setStartTime(DateUtils.stringToDate("31/12/1998 21:37"));
        availability.setEndTime(DateUtils.stringToDate("31/12/1998 23:37"));
        Teacher teacher = teacherService.save(createTeacher("teacher_1"));
        Course course = new Course();
        course.setDescription("Course Math");
        course.setCategory(Category.PRIMAIRE);
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


        Reservation reservation = new Reservation();
        reservation.setDate(DateUtils.stringToDate("31/12/2020 21:37"));
        reservation.setStartTime(DateUtils.stringToDate("31/12/1998 21:37"));
        reservation.setEndTime(DateUtils.stringToDate("31/12/1998 21:37"));
        reservation.setStatus(ReservationStatus.BOOKED);
        reservation.setStudent(StudentServiceTest.createStudent("Tan"));
        reservation.setCourse(availability.getTeacherCourse());
        Mode mode = new Mode();
        mode.setModeName(ModeType.ONLINE);
        mode = modeRepository.save(mode);

        reservation.setMode(mode);
        try {
            reservation = service.makeAReservation(reservation);
        } catch (RuntimeException ex) {
            System.out.println("didn't succeded");
        }
        // TODO : Fix this issue with the @Transactional
        //  availability = availabilityService.findById(availability.getId());
        //   assertEquals(availability.isBooked(),false);
        // reservation = service.findById(reservation.getId());
        assertEquals(reservation.getStatus(), ReservationStatus.PAYED);
        assertNotNull(reservation.getId());

    }
}