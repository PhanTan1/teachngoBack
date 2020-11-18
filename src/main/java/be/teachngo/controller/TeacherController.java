package be.teachngo.controller;

import be.teachngo.data.Teacher;
import be.teachngo.data.TeacherCourse;
import be.teachngo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class TeacherController {

    public static final String UPDATE_TEACHER_PATH = "teacher/updateTeacher";

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> index() {
        return teacherService.getAllTeachers();
    }
    @GetMapping("/teachers/{postalCode}")
    public List<Teacher> findTeachersByPostalCode(@PathVariable String postalCode) {
        List<Teacher> teachers = teacherService.getAllTeachersAvailableOn(postalCode);
        return teachers;
    }

    /*@GetMapping("/teachers")
    public List < Teacher > getUsers() {
        return this.teacherService.findAll();
    } */

    @PostMapping("/teachers")
    Teacher newTeacher(@RequestBody Teacher newTeacher) {
        return teacherService.save(newTeacher);
    }


    @PostMapping("/teachers/addCourse")
    TeacherCourse addCourseToTeacher(@RequestBody TeacherCourse teacherCourse) {
        return teacherService.addCourseToTeacher(teacherCourse);
    }

    @DeleteMapping("/teachers")
    void deleteTeacher(@RequestBody Teacher newTeacher) {
        teacherService.removeById(newTeacher.getId());
    }


}
