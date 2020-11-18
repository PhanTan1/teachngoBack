package be.teachngo.service;

import be.teachngo.data.Availability;
import be.teachngo.data.Teacher;

import java.util.List;

public interface AvailabilityService {

    Availability save(Availability availability);

    List<Availability> findAvailabilitiesOfTeacher(Teacher teacher);

    List<Availability> findAvailabilitiesOfTeacher(Long id);

    /**
     * Returns all the availibility starting from now depending on parameter booked:
     * if booked = true => return only the booked availabilities
     * if booked = fase => return only not booked availabilities
     *
     * @param teacher
     * @param booked
     * @return
     */
    List<Availability> findAvailabilitiesOfTeacherStartFromNow(Teacher teacher, boolean booked);

    /**
     * Returns all the availibility starting from now depending on parameter booked:
     * if booked = true => return only the booked availabilities
     * if booked = fase => return only not booked availabilities
     *
     * @param id
     * @param booked
     * @return
     */
    List<Availability> findAvailabilitiesOfTeacherStartFromNow(Long id, boolean booked);


    Availability findById(Long id);
}
