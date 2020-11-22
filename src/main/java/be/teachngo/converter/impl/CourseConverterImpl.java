package be.teachngo.converter.impl;

import be.teachngo.converter.CourseConverter;
import be.teachngo.converter.TeacherConverter;
import be.teachngo.data.Course;
import be.teachngo.data.TeacherCourse;
import be.teachngo.dto.CourseDTO;
import be.teachngo.repository.TeacherCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseConverterImpl implements CourseConverter {

    @Autowired
    private TeacherConverter teacherConverter;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    public CourseDTO convert(Course course) {
        List<TeacherCourse> teacherCourses = teacherCourseRepository.findByCourseId(course.getId());
        return CourseDTO.builder()
                .id(course.getId())
                .description(course.getDescription())
                .size(course.getSize())
                .subject(course.getSubject())
                .category(course.getCategory())
                .teachers(teacherCourses
                        .stream()
                        .map(tc -> teacherConverter.convert(tc))
                        .collect(Collectors.toList()))
                .build();
    }
}
