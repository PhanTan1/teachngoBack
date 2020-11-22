package be.teachngo.controller;


import be.teachngo.converter.CourseConverter;
import be.teachngo.data.Category;
import be.teachngo.data.Course;
import be.teachngo.dto.CourseDTO;
import be.teachngo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseConverter courseConverter;

    /**
     * Not authenticated
     *
     * @param category
     * @return
     */
    @GetMapping("/courses/bycategory/{category}")
    public List<CourseDTO> findByCategory(@PathVariable String category) {
        Category valueOfCategory = Category.valueOf(category.toUpperCase());
        List<CourseDTO> coursesOf = courseService.getAllCoursesOf(valueOfCategory)
                .stream()
                .map(courseConverter::convert)
                .collect(Collectors.toList());
        return coursesOf;
    }

    /**
     * Not authenticated
     *
     * @param subject
     * @return
     */
    @GetMapping("/courses/bysubject/{subject}")
    public List<CourseDTO> findBySubject(@PathVariable String subject) {
        List<CourseDTO> coursesOf = courseService.getAllCoursesOf(subject)
                .stream()
                .map(courseConverter::convert)
                .collect(Collectors.toList());
        return coursesOf;
    }

    @GetMapping("/courses")
    public List<CourseDTO> getCourses() {
        List<CourseDTO> all = this.courseService.findAll()
                .stream()
                .map(courseConverter::convert)
                .collect(Collectors.toList());
        return all;
    }

    @PostMapping("/courses")
    public Course newCourses(@RequestBody Course course) {
        return this.courseService.save(course);
    }
}
