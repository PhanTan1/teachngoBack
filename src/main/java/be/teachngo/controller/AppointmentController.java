package be.teachngo.controller;

import be.teachngo.converter.AvailabilityConverter;
import be.teachngo.dto.Appointment;
import be.teachngo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class AppointmentController {

    @Autowired
    private AvailabilityConverter availabilityConverter;
    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/teachers/availabilities/{id}")
    List<Appointment> findAvailabilitiesOfTeacher(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfTeacherStartFromNow(id, false)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }

    @GetMapping("/courses/availabilities/{id}")
    List<Appointment> findAvailabilitiesOfCourse(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfTeacherStartFromNow(id, false)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }

    @GetMapping("/teachers/availabilities/{id}/booked")
    List<Appointment> findBookedAvailabilities(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfTeacherStartFromNow(id, true)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }

    @GetMapping("/courses/availabilities/{id}/booked")
    List<Appointment> findBookedAvailabilitiesOfCourses(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfCourseStartFromNow(id, true)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }

    /**
     * @param id : the teacher id
     * @return
     */
    @GetMapping("/teachers/availabilities/{id}/all")
    List<Appointment> findAllAvailabilities(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfTeacher(id)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }

    /**
     * @param id : the course id
     * @return
     */
    @GetMapping("/courses/availabilities/{id}/all")
    List<Appointment> findAllAvailabilitiesOfCourses(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfCourse(id)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }


}
