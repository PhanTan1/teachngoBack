package be.teachngo.converter;

import be.teachngo.data.Course;
import be.teachngo.dto.CourseDTO;

public interface CourseConverter {

    CourseDTO convert(Course course);
}
