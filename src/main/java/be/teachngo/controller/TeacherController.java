package be.teachngo.controller;

import be.teachngo.data.Teacher;
import be.teachngo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers/{postalCode}")
    public List <Teacher> index(@PathVariable String postalCode) {
        List<Teacher> teachers = teacherService.getAllTeachersAvailableOn(postalCode);
        return teachers;
    }

    @GetMapping("/teachers")
    public List < Teacher > getUsers() {
        return this.teacherService.findAll();
    }

    @PostMapping("/teachers")
    Teacher newTeacher(@RequestBody Teacher newTeacher) {
        return teacherService.save(newTeacher);
    }

    @DeleteMapping("/teachers")
    void deleteTeacher(@RequestBody Teacher newTeacher) {
         teacherService.removeById(newTeacher.getId());
    }


}
