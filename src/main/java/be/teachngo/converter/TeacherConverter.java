package be.teachngo.converter;

import be.teachngo.data.TeacherCourse;
import be.teachngo.dto.TeacherDTO;

public interface TeacherConverter {

    TeacherDTO convert(TeacherCourse teacherCourse);
}
