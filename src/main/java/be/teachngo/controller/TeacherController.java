package be.teachngo.controller;

import be.teachngo.data.Teacher;
import be.teachngo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teacher")
    public String index(String postalCode) {
        List<Teacher> teachers = teacherService.getAllTeachersAvailableOn(postalCode);
        teachers.forEach(teacher -> {
        });
        return postalCode;
    }

    @GetMapping("/teachers")
    public List < Teacher > getUsers() {
        return this.teacherService.findAll();
    }
}
