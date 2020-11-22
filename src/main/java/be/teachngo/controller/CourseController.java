package be.teachngo.controller;


import be.teachngo.data.Category;
import be.teachngo.data.Course;
import be.teachngo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * Not authenticated
     *
     * @param category
     * @return
     */
    @GetMapping("/courses/bycategory/{category}")
    public List<Course> findByCategory(@PathVariable String category) {
        Category valueOfCategory = Category.valueOf(category.toUpperCase());
        List<Course> coursesOf = courseService.getAllCoursesOf(valueOfCategory);
        return coursesOf;
    }

    /**
     * Not authenticated
     *
     * @param subject
     * @return
     */
    @GetMapping("/courses/bysubject/{subject}")
    public List<Course> findBySubject(@PathVariable String subject) {
        List<Course> coursesOf = courseService.getAllCoursesOf(subject);
        return coursesOf;
    }

    @GetMapping("/courses")
    public List<Course> getCourses() {
        List<Course> all = this.courseService.findAll();
        return all;
    }

    @PostMapping("/courses")
    public Course newCourses(@RequestBody Course course) {
        return this.courseService.save(course);
    }
}
