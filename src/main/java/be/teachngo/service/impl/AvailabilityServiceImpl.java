package be.teachngo.service.impl;

import be.teachngo.data.Availability;
import be.teachngo.data.Course;
import be.teachngo.data.Teacher;
import be.teachngo.data.TeacherCourse;
import be.teachngo.repository.AvailabilityRepository;
import be.teachngo.repository.TeacherCourseRepository;
import be.teachngo.repository.TeacherRepository;
import be.teachngo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Override
    public Availability save(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public List<Availability> findAvailabilitiesOfTeacher(Teacher teacher) {
        if (teacher == null) {
            return Collections.emptyList();
        } else {
            availabilityRepository.findAll();
            return availabilityRepository.findByTeacherCourseTeacher(teacher);
        }
    }

    @Override
    public List<Availability> findAvailabilitiesOfTeacher(Long id) {
        Teacher teacher = teacherRepository.getOne(id);

        return findAvailabilitiesOfTeacher(teacher);

    }

    @Override
    public List<Availability> findAvailabilitiesOfCourse(Course course) {
        if (course == null || course.getId() == null) {
            return Collections.emptyList();
        }
        return findAvailabilitiesOfCourse(course.getId());
    }

    @Override
    public List<Availability> findAvailabilitiesOfCourse(Long id) {
        // first we will look for all the teachers giving this course
        List<TeacherCourse> byCourseId = teacherCourseRepository.findByCourseId(id);
        List<Availability> allAvailabilities = byCourseId.stream()
                .flatMap(teacherCourse -> findAvailabilitiesOfTeacher(teacherCourse.getTeacher().getId())
                        .stream())
                .collect(Collectors.toList());
        return allAvailabilities;
    }

    @Override
    public List<Availability> findAvailabilitiesOfTeacherStartFromNow(Teacher teacher, boolean booked) {
        final Date now = new Date();
        return getFilteredAvailabilities(booked, now, findAvailabilitiesOfTeacher(teacher));
    }

    private List<Availability> getFilteredAvailabilities(boolean booked, Date now, List<Availability> availabilitiesOfTeacher) {
        return availabilitiesOfTeacher
                .stream()
                .filter(av -> av.getStartTime().after(now))
                .filter(av -> (booked && av.isBooked()) || (!booked && !av.isBooked()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Availability> findAvailabilitiesOfTeacherStartFromNow(Long id, boolean booked) {
        final Date now = new Date();
        return getFilteredAvailabilities(booked, now, findAvailabilitiesOfTeacher(id));
    }

    @Override
    public List<Availability> findAvailabilitiesOfCourseStartFromNow(Course course, boolean booked) {
        return getFilteredAvailabilities(booked, new Date(), findAvailabilitiesOfCourse(course));
    }

    @Override
    public List<Availability> findAvailabilitiesOfCourseStartFromNow(Long id, boolean booked) {
        return getFilteredAvailabilities(booked, new Date(), findAvailabilitiesOfCourse(id));
    }

    @Override
    public Availability findById(Long id) {
        return availabilityRepository.getOne(id);
    }
}
