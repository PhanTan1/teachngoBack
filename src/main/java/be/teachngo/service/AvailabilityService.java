package be.teachngo.service;

import be.teachngo.data.Availability;
import be.teachngo.data.Teacher;

import java.util.List;

public interface AvailabilityService {

    Availability save(Availability availability);

    List<Availability> findAvailabilitiesOfTeacher(Teacher teacher);

    List<Availability> findAvailabilitiesOfTeacher(Long id);

    List<Availability> findAvailabilitiesOfTeacherStartFromNow(Teacher teacher);

    List<Availability> findAvailabilitiesOfTeacherStartFromNow(Long id);


}
