package be.teachngo.service.impl;

import be.teachngo.data.*;
import be.teachngo.repository.*;
import be.teachngo.service.PayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PayementServiceImpl implements PayementService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PayementRepository payementRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AdministratorRepository administratorRepository;


    @Override
    @Transactional
    public Payement makeAPayement(Payement payement) {
        if (payement == null) {
            throw new IllegalArgumentException("payement can't be null");
        }

        if (payement.getSenderAccountId() == null) {
            throw new IllegalArgumentException("Sender Account Id is messing");
        }
        Account sender = accountRepository.getOne(payement.getSenderAccountId());
        if (sender instanceof PayPalAccount) {
            payement.setMode(PaymentMode.PAYPAL);
        } else {
            payement.setMode(PaymentMode.WIDTHROUG);
        }
        payement.setSender(sender);

        // look for the admin account
        List<Administrator> all = administratorRepository.findAll();
        if (all.isEmpty()) {
            throw new IllegalStateException("We should have at least one Admin");
        }
        List<Account> accounts = accountRepository.findByUser(all.get(0));
        if (accounts.isEmpty()) {
            throw new IllegalStateException("The admin should have at least one account");
        }
        payement.setReciever(accounts.get(0));
        if (payement.getReservation() == null || payement.getReservation().getId() == null) {
            throw new IllegalArgumentException("A payement must be linked to a reservaiton");
        }
        Reservation reservation = reservationRepository.getOne(payement.getReservation().getId());
        payement.setReservation(reservation);

        if (reservation.getAvailability() == null || reservation.getAvailability().getId() == null) {
            throw new IllegalStateException("A reservation must be linked to an availability");
        }

        reservation.getAvailability().setBooked(true);

        availabilityRepository.save(reservation.getAvailability());
        reservation.setStatus(ReservationStatus.PAYED);
        reservation = reservationRepository.save(reservation);
        payement.setReservation(reservation);
        payement.setDate(new Date());
        payement.setStatus(PayementStatus.CONFIRMED);
        payement = payementRepository.save(payement);
        return payement;
    }


    @Override
    public List<Payement> getAllPayements() {
        return payementRepository.findAll();
    }
}
