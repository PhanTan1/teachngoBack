package be.teachngo.converter.impl;


import be.teachngo.converter.TeacherConverter;
import be.teachngo.data.TeacherCourse;
import be.teachngo.dto.TeacherDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverterImpl implements TeacherConverter {


    @Override
    public TeacherDTO convert(TeacherCourse teacherCourse) {

        return TeacherDTO.builder()
                .id(teacherCourse.getTeacher().getId())
                .login(teacherCourse.getTeacher().getLogin())
                .email(teacherCourse.getTeacher().getEmail())
                .firstName(teacherCourse.getTeacher().getFirstName())
                .lastName(teacherCourse.getTeacher().getLastName())
                .adress(teacherCourse.getTeacher().getAdress())
                .phone(teacherCourse.getTeacher().getPhone())
                .gender(teacherCourse.getTeacher().getGender())
                .biography(teacherCourse.getTeacher().getBiography())
                .price(teacherCourse.getPrice())
                .teacherCoursID(teacherCourse.getId())
                .active(teacherCourse.getTeacher().isActive())
                .build();
    }
}
