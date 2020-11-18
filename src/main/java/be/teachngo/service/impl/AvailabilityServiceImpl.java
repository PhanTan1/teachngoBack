package be.teachngo.service.impl;

import be.teachngo.data.Availability;
import be.teachngo.data.Teacher;
import be.teachngo.repository.AvailabilityRepository;
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
    public List<Availability> findAvailabilitiesOfTeacherStartFromNow(Teacher teacher, boolean booked) {
        final Date now = new Date();
        return findAvailabilitiesOfTeacher(teacher)
                .stream()
                .filter(av -> av.getStartTime().after(now))
                .filter(av -> (booked && av.isBooked()) || (!booked && !av.isBooked()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Availability> findAvailabilitiesOfTeacherStartFromNow(Long id, boolean booked) {
        final Date now = new Date();
        return findAvailabilitiesOfTeacher(id)
                .stream()
                .filter(av -> av.getStartTime().after(now))
                .filter(av -> (booked && av.isBooked()) || (!booked && !av.isBooked()))
                .collect(Collectors.toList());
    }

    @Override
    public Availability findById(Long id) {
        return availabilityRepository.getOne(id);
    }
}
