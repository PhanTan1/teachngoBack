package be.teachngo.controller;

import be.teachngo.data.Teacher;
import be.teachngo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
    public String index(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        model.addAttribute("newTeacher", new Teacher());
        return "teacher/teachers";
    }

    @PostMapping("teachers/add")
    public String addTeacher(@ModelAttribute Teacher newTeacher) {
        teacherService.addTeacher(newTeacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/update/{id}")
    public String updateTeacher(@PathVariable Long id, Model model) {
        Teacher teacher = teacherService.getTeacher(id);
        model.addAttribute("teacher", teacher);
        return UPDATE_TEACHER_PATH;
    }

    @GetMapping("/teachers/{postalCode}")
    public List <Teacher> index(@PathVariable String postalCode) {
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

    @DeleteMapping("/teachers")
    void deleteTeacher(@RequestBody Teacher newTeacher) {
         teacherService.removeById(newTeacher.getId());
    }


}
