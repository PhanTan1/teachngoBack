package be.teachngo.controller;

import be.teachngo.data.Availability;
import be.teachngo.data.Teacher;
import be.teachngo.data.TeacherCourse;
import be.teachngo.mail.MailConstructor;
import be.teachngo.service.AvailabilityService;
import be.teachngo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class TeacherController {

    public static final String UPDATE_TEACHER_PATH = "teacher/updateTeacher";

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private PasswordEncoder passwordEncoderBean;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @GetMapping("/teachers")
    public List<Teacher> index() {
        return teacherService.getAllTeachers();
    }
    @GetMapping("/teachers/{postalCode}")
    public List<Teacher> findTeachersByPostalCode(@PathVariable String postalCode) {
        List<Teacher> teachers = teacherService.getAllTeachersAvailableOn(postalCode);
        teachers.stream()
                .forEach(teacher -> teacher.setPassword("XXXXXXXX"));
        return teachers;
    }

    /*@GetMapping("/teachers")
    public List < Teacher > getUsers() {
        return this.teacherService.findAll();
    } */

    @PostMapping("/teachers")
    Teacher newTeacher(@RequestBody Teacher newTeacher, HttpServletRequest request) {
        String token = UUID.randomUUID().toString();
        newTeacher.setToken(token);
        newTeacher.setPassword(passwordEncoderBean.encode(newTeacher.getPassword()));
        final Teacher teacher = teacherService.save(newTeacher);
        // TODO : May be this will be not working when we dockerise the app ?!
        new Thread(() -> {
            String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, teacher);
            mailSender.send(newEmail);
        }).start();

        teacher.setPassword("XXXXXXX");
        return teacher;
    }


    @PostMapping("/teachers/addCourse")
    TeacherCourse addCourseToTeacher(@RequestBody TeacherCourse teacherCourse) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Teacher) {
            Teacher teacher = (Teacher) authentication.getPrincipal();
            teacherCourse.setTeacher(teacher);
            return teacherService.addCourseToTeacher(teacherCourse);
        } else {
            throw new IllegalArgumentException("Authorisation issue");
        }
    }

    @DeleteMapping("/teachers")
    void deleteTeacher(@RequestBody Teacher newTeacher) {
        teacherService.removeById(newTeacher.getId());
    }

    @PostMapping("/availabilities")
    Availability newAvailability(@RequestBody Availability availability) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Teacher) {
            Teacher teacher = (Teacher) authentication.getPrincipal();
            availability.getTeacherCourse().setTeacher(teacher);
            return availabilityService.save(availability);
        } else {
            throw new IllegalArgumentException("Authorisation issue");
        }

    }


}
