package be.teachngo.service.impl;

import be.teachngo.data.Country;
import be.teachngo.data.Course;
import be.teachngo.data.Teacher;
import be.teachngo.data.TeacherCourse;
import be.teachngo.repository.CourseRepository;
import be.teachngo.repository.TeacherCourseRepository;
import be.teachngo.repository.TeacherRepository;
import be.teachngo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }


    @Override
    public List<Teacher> getAllTeachersAvailableOn(String postalCode) {
        return teacherRepository.findByAdressPostalCode(postalCode);
    }

    @Override
    public List<Teacher> getAllTeachersAvailableOn(Country country) {
        return teacherRepository.findByAdressCountry(country);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void removeAll() {
        teacherRepository.deleteAll();
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void removeById(Long id) {
         teacherRepository.deleteById(id);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override // A vérifier
    public void addCourseToTeacher(Teacher teacher, TeacherCourse teacherCourse) {
        List<TeacherCourse> byTeacher = teacherCourseRepository.findByTeacher(teacher);
        boolean alreadyExist = byTeacher != null && byTeacher
                .stream()
                .anyMatch(at -> at.getCourse().getId()
                        .equals(teacherCourse.getCourse().getId()));
        if (!alreadyExist) {
            teacherCourse.setTeacher(teacher);
            teacherCourseRepository.save(teacherCourse);
        }

    }

    @Override // A vérifier
    public void removeCourseToTeacher(Teacher teacher, TeacherCourse teacherCourse) {
        List<TeacherCourse> byTeacher = teacherCourseRepository.findByTeacher(teacher);

        if (byTeacher != null) {
            Optional<TeacherCourse> first = byTeacher.stream()
                    .filter(at -> at.getCourse().getId().equals(teacherCourse.getCourse().getId()))
                    .findFirst();
            if (first.isPresent()) {
                teacherCourseRepository.delete(first.get());
            }
        }

    }
}
