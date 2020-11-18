package be.teachngo.service;

import be.teachngo.data.Availability;
import be.teachngo.data.Payement;
import be.teachngo.data.Reservation;

public interface PayementService {

    /**
     * The availibality will be booked only if the payement is done
     * If the payement will be not succeed, the reservation will have the status of Booked
     * and the availability will be not bookd
     *
     * @param reservation
     * @param payement
     * @param availability
     * @return
     */
    Payement makeAPayement(Reservation reservation, Payement payement, Availability availability);
}