package be.teachngo.service;

import be.teachngo.data.Reservation;
import be.teachngo.exception.PayementException;
import be.teachngo.exception.PayementProcessException;

import java.util.List;


public interface ReservationService {

    /**
     * Check the data in the reservation and create a new reservation if all data are valid
     * This service must be transactional; When the payment persistance is failed, the reservation persisting must be rolledback.
     *
     * @param reservation
     * @return
     * @throws IllegalArgumentException when the starddate and End date are not valid, the mode isn't available for the teacher
     * @throws IllegalStateException    when the teacherCourse, student, mode are not persisted
     * @throws PayementException        when the payment isn't valid
     * @throws PayementProcessException when the oersistig of payment is failed
     */

    Reservation makeAReservation(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> getAllReservations();
}
