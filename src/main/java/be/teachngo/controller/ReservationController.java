package be.teachngo.controller;


import be.teachngo.data.Reservation;
import be.teachngo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservations")
    Reservation newAvailability(@RequestBody Reservation reservation) {
        return reservationService.makeAReservation(reservation);
    }

    @GetMapping("/reservations")
    List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
}
