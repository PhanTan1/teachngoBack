package be.teachngo.service;

import be.teachngo.data.Payement;

import java.util.List;

public interface PayementService {

    /**
     * The availibality will be booked only if the payement is done
     * If the payement will be not succeed, the reservation will have the status of Booked
     * and the availability will be not bookd
     *
     * @param payement
     * @return
     */
    Payement makeAPayement(Payement payement);

    List<Payement> getAllPayements();
}