package be.teachngo.controller;

import be.teachngo.data.Student;
import be.teachngo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class StudentController {

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

    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent) {
        return studentService.save(newStudent);
    }

    @DeleteMapping("/students")
    void deleteStudent(@RequestBody Student newStudent) {
        studentService.removeById(newStudent.getId());
    }
}
