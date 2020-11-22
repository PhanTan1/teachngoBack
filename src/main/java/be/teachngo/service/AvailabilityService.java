package be.teachngo.service;

import be.teachngo.data.Availability;
import be.teachngo.data.Course;
import be.teachngo.data.Teacher;

import java.util.List;

public interface AvailabilityService {

    Availability save(Availability availability);

    List<Availability> findAvailabilitiesOfTeacher(Teacher teacher);

    List<Availability> findAvailabilitiesOfTeacher(Long id);

    List<Availability> findAvailabilitiesOfCourse(Course course);

    List<Availability> findAvailabilitiesOfCourse(Long id);

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
     * @param id : the teacher id
     * @param booked
     * @return
     */
    List<Availability> findAvailabilitiesOfTeacherStartFromNow(Long id, boolean booked);

    /**
     * Returns all the availibility starting from now depending on parameter booked:
     * if booked = true => return only the booked availabilities
     * if booked = fase => return only not booked availabilities
     *
     * @param course
     * @param booked
     * @return
     */
    List<Availability> findAvailabilitiesOfCourseStartFromNow(Course course, boolean booked);

    /**
     * Returns all the availibility starting from now depending on parameter booked:
     * if booked = true => return only the booked availabilities
     * if booked = fase => return only not booked availabilities
     *
     * @param id     : the course ID
     * @param booked
     * @return
     */
    List<Availability> findAvailabilitiesOfCourseStartFromNow(Long id, boolean booked);


    Availability findById(Long id);
}
