package be.teachngo.controller;

import be.teachngo.data.Student;
import be.teachngo.mail.MailConstructor;
import be.teachngo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoderBean;



    @GetMapping("/students/{postalCode}")
    public List<Student> index(@PathVariable String postalCode) {
        List<Student> students = studentService.getAllStudentsAvailableOn(postalCode);
        return students;
    }

    @GetMapping("/students")
    public List<Student> getUsers() {
        List<Student> all = this.studentService.findAll();
        // security
        all.stream()
                .forEach(student -> student.setPassword("XXXXXXXX"));
        return all;
    }


    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent, HttpServletRequest request) {
        // Generate the token
        String token = UUID.randomUUID().toString();
        newStudent.setToken(token);
        newStudent.setPassword(passwordEncoderBean.encode(newStudent.getPassword()));
        final Student student = studentService.save(newStudent);
        new Thread(() -> {
            String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, student);
            mailSender.send(newEmail);
        }).start();
        student.setPassword("XXXXXXX");
        return student;
    }

    @DeleteMapping("/students")
    void deleteStudent(@RequestBody Student newStudent) {
        studentService.removeById(newStudent.getId());
    }
}
