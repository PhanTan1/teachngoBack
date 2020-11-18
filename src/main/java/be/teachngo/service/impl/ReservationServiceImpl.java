package be.teachngo.service.impl;

import be.teachngo.data.Reservation;
import be.teachngo.data.ReservationStatus;
import be.teachngo.data.Student;
import be.teachngo.data.TeacherCourse;
import be.teachngo.repository.ModeRepository;
import be.teachngo.repository.ReservationRepository;
import be.teachngo.repository.StudentRepository;
import be.teachngo.repository.TeacherCourseRepository;
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

    @Override
    public Reservation makeAReservation(final Reservation reservation) {
        // TODO : check that times are inside the range of Availability


        if (reservation.getCourse() == null || reservation.getCourse().getId() == null) {
            // we can't make a reservation for not existing course
            throw new IllegalArgumentException("course can't be null");
        }
        if (reservation.getMode() == null || reservation.getMode().getModeId() == null) {
            // we can't make a reservation without defined mode
            throw new IllegalArgumentException("mode can't be null");

        }

        // TODO : delete this line once we implement the security
        reservation.setStudent(studentRepository.findAll().get(0));
        // validate that the Student haven't another booked course at the same dates
        Optional<Reservation> inter = reservationRepository.findByStudent(reservation.getStudent())
                .stream()
                .filter(reser -> intersection(reservation, reser))
                .findFirst();
        if (inter.isPresent()) {
            Reservation reservation1 = inter.get();
            reservation1.setErrorMessage("You have already this reservation in the selected time slot ");
            return reservation1;
        }
        reservation.setStatus(ReservationStatus.BOOKED);
        TeacherCourse persistedCourse = teacherCourseRepository.getOne(reservation.getCourse().getId());
        reservation.setCourse(persistedCourse);
        reservation.setMode(modeRepository.getOne(reservation.getMode().getModeId()));
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
