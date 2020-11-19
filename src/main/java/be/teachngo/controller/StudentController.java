package be.teachngo.controller;

import be.teachngo.data.Student;
import be.teachngo.mail.MailConstructor;
import be.teachngo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class StudentController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;
    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{postalCode}")
    public List<Student> index(@PathVariable String postalCode) {
        List<Student> students = studentService.getAllStudentsAvailableOn(postalCode);
        return students;
    }

    @GetMapping("/students")
    public List<Student> getUsers() {
        return this.studentService.findAll();
    }

    @GetMapping("/newUser")
    public Student getUsers(@RequestParam("token") String token, @RequestParam("login") String login) {
        Student student = studentService.findByLogin(login);
        if (token != null
                && student != null
                && !student.isActive()
                && student.getToken().equals(token)) {

            student.setActive(true);
            student = studentService.save(student);
        }
        return student;
    }

    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent, HttpServletRequest request) {
        // Generate the token
        String token = UUID.randomUUID().toString();
        newStudent.setToken(token);
        newStudent = studentService.save(newStudent);
        // TODO : May be this will be not working when we dockerise the app ?!
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, newStudent);
        mailSender.send(newEmail);
        return newStudent;
    }

    @DeleteMapping("/students")
    void deleteStudent(@RequestBody Student newStudent) {
        studentService.removeById(newStudent.getId());
    }
}
