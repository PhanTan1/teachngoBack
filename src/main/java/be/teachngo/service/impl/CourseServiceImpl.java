package be.teachngo.service.impl;

import be.teachngo.data.Category;
import be.teachngo.data.Course;
import be.teachngo.repository.CourseRepository;
import be.teachngo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<Course> getAllCoursesOf(Category category) {
        return courseRepository.findByCategory(category);
    }

    @Override
    public List<Course> getAllCoursesOf(String subject) {
        return courseRepository.findBySubject(subject);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
