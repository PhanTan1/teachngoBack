package be.teachngo.service.impl;

import be.teachngo.data.Availability;
import be.teachngo.data.Payement;
import be.teachngo.data.Reservation;
import be.teachngo.data.ReservationStatus;
import be.teachngo.repository.AvailabilityRepository;
import be.teachngo.repository.PayementRepository;
import be.teachngo.repository.ReservationRepository;
import be.teachngo.service.PayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayementServiceImpl implements PayementService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PayementRepository payementRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;


    @Override
    @Transactional
    public Payement makeAPayement(Reservation reservation, Payement payement, Availability availability) {
        availability.setBooked(true);
        availabilityRepository.save(availability);
        reservation.setStatus(ReservationStatus.PAYED);
        reservation = reservationRepository.save(reservation);
        payement.setReservation(reservation);
        payement = payementRepository.save(payement);
        return payement;
    }
}
