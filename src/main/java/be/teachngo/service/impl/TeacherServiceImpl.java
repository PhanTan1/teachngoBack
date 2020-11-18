package be.teachngo.service.impl;

import be.teachngo.data.*;
import be.teachngo.repository.AddressRepository;
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

    @Autowired
    private AddressRepository addressRepository;

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
        // to avoid the detached entity passed to persist: be.teachngo.data.Address exception

        if (teacher.getId() == null
                && teacher.getAdress() != null
                && teacher.getAdress().getId() != null) {
            Address address = addressRepository.getOne(teacher.getAdress().getId());
            teacher.setAdress(address);
        }


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
    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override // A vérifier
    public TeacherCourse addCourseToTeacher(TeacherCourse teacherCourse) {
        List<TeacherCourse> byTeacher = teacherCourseRepository.findByTeacherId(teacherCourse.getTeacher().getId());
        Optional<TeacherCourse> b = byTeacher != null ? byTeacher
                .stream()
                .filter(at -> at.getCourse().getId()
                        .equals(teacherCourse.getCourse().getId()))
                .findFirst() : Optional.ofNullable(null);
        if (!b.isPresent()) {
            if (teacherCourse.getId() == null) {
                if (teacherCourse.getCourse() != null
                        && teacherCourse.getCourse().getId() != null) {
                    Course course = courseRepository.getOne(teacherCourse.getCourse().getId());
                    teacherCourse.setCourse(course);
                }
                if (teacherCourse.getTeacher() != null
                        && teacherCourse.getTeacher().getId() != null) {
                    Teacher teacher = teacherRepository.getOne(teacherCourse.getTeacher().getId());
                    teacherCourse.setTeacher(teacher);
                }
            }
            return teacherCourseRepository.save(teacherCourse);
        } else {
            return b.get();
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
