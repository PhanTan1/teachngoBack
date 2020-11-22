package be.teachngo.service;

import be.teachngo.data.Category;
import be.teachngo.data.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCoursesOf(Category category);

    List<Course> getAllCoursesOf(String subject);

    List<Course> findAll();

    Course save(Course course);
}
