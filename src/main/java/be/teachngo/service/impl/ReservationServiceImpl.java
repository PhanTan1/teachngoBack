package be.teachngo.service.impl;

import be.teachngo.data.*;
import be.teachngo.repository.*;
import be.teachngo.service.PayementService;
import be.teachngo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PayementService payementService;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModeRepository modeRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public Reservation makeAReservation(final Reservation reservation) {

        Availability availability = reservation.getAvailability();
        if (availability == null || availability.getId() == null) {
            // we can't make a reservation for not existing course
            throw new IllegalArgumentException("availability can't be null");
        }
        availability = availabilityRepository.getOne(availability.getId());
        reservation.setAvailability(availability);
        reservation.setStartTime(availability.getStartTime());
        reservation.setEndTime(availability.getEndTime());


        if (availability.getTeacherCourse() == null || availability.getTeacherCourse().getId() == null) {
            // we can't make a reservation for not existing course
            throw new IllegalStateException("course can't be null in an availability");
        }
        // TODO : delete this line once we implement the security
        reservation.setStudent(studentRepository.findAll().get(0));
        // validate that the Student haven't another booked course at the same dates
        Optional<Reservation> inter = reservationRepository.findByStudentId(reservation.getStudent().getId())
                .stream()
                .filter(reser -> reservation.getStartTime().equals(reser.getStartTime()) && reservation.getEndTime().equals(reser.getEndTime()))
                .findFirst();
        if (inter.isPresent()) {
            Reservation reservation1 = inter.get();
            reservation1.setErrorMessage("You have already this reservation in the selected time slot ");
            return reservation1;
        }
        reservation.setStatus(ReservationStatus.BOOKED);
        TeacherCourse persistedCourse = teacherCourseRepository.getOne(availability.getTeacherCourse().getId());
        reservation.setCourse(persistedCourse);
        reservation.setMode(modeRepository.getOne(1l));
        // update the price
        reservation.setPrice(reservation.getCourse().getPrice());
        return reservationRepository.save(reservation);
    }

    //TODO : Test this method
    boolean intersection(Reservation r1, Reservation r2) {
        if (r1.getStartTime().after(r2.getStartTime()) && r1.getStartTime().before(r2.getEndTime())) {
            // r1 =  -----------17-----------19
            // r2 = ------16-----------20
            // case2
            // r1 =  -----------17-----------19
            // r2 = ------16---------------------20
            return true;
        }
        if (r1.getEndTime().after(r2.getStartTime()) && r1.getEndTime().before(r2.getEndTime())) {
            // r1 =  ---------17-----------19
            // r2 = -----------------18---------20------
            return true;
        }
        if (r2.getStartTime().after(r1.getStartTime()) && r2.getStartTime().before(r1.getEndTime())) {
            // r1 =  ----16-----------19
            // r2 = --------17----18------
            return true;
        }
        return false;
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.getOne(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        // TODO : delete this line once we implement the security
        Student student = studentRepository.findAll().get(0);

        return reservationRepository.findByStudent(student);
    }
}
