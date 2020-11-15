package be.teachngo.service;

import be.teachngo.data.Country;
import be.teachngo.data.Course;
import be.teachngo.data.Teacher;
import be.teachngo.data.TeacherCourse;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachersAvailableOn(String postalCode);

    List<Teacher> getAllTeachersAvailableOn(Country country);

    Teacher save(Teacher teacher);

    void removeAll();

    List<Teacher> findAll();

    void removeById(Long id);

    List<Course> getAllCourses();

    List<Teacher> getAllTeachers();

    Teacher getTeacher(Long id);

    void addTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void addCourseToTeacher(Teacher teacher, TeacherCourse teacherCourse);

    void removeCourseToTeacher(Teacher teacher, TeacherCourse teacherCourse);


}
