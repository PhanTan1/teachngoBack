package be.teachngo.controller;

import be.teachngo.converter.AvailabilityConverter;
import be.teachngo.data.Availability;
import be.teachngo.dto.Appointment;
import be.teachngo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class AppointmentController {

    private final static SimpleDateFormat formatter5 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    @Autowired
    private AvailabilityConverter availabilityConverter;
    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/availabilities/{id}")
    List<Appointment> findAvailabilities(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfTeacherStartFromNow(id, false)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }

    @GetMapping("/availabilities/{id}/booked")
    List<Appointment> findBookedAvailabilities(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfTeacherStartFromNow(id, true)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }

    /**
     * @param id : the teacher id
     * @return
     */
    @GetMapping("/availabilities/{id}/all")
    List<Appointment> findAllAvailabilities(@PathVariable Long id) {

        return availabilityService.findAvailabilitiesOfTeacher(id)
                .stream()
                .map(availabilityConverter::convertToAppointment)
                .collect(Collectors.toList());
    }

    @PostMapping("/availabilities")
    Availability newAvailability(@RequestBody Availability availability) {
        return availabilityService.save(availability);
    }
}
