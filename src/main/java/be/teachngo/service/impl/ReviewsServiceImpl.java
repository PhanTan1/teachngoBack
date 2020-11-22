package be.teachngo.service.impl;

import be.teachngo.data.Reservation;
import be.teachngo.data.ReservationStatus;
import be.teachngo.data.Review;
import be.teachngo.data.Student;
import be.teachngo.repository.ReservationRepository;
import be.teachngo.repository.ReviewRepository;
import be.teachngo.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Review> getAllReviewsOfTeacher(Long id) {
        return reviewRepository.findByReservationCourseTeacherId(id);
    }

    @Override
    public List<Review> getAllReviewsOfStudent(Long id) {
        return reviewRepository.findByReservationStudentId(id);
    }

    @Override
    public Review save(Review review) {
        if (review == null
                || review.getReservation() == null
                || review.getReservation().getId() == null) {
            throw new IllegalArgumentException("Can't add a review ");
        }

        Reservation reservation = reservationRepository.getOne(review.getReservation().getId());
        if (!reservation.getStatus().equals(ReservationStatus.PAYED)) {
            throw new IllegalArgumentException("Can't add a review to not payed reservation");

        }
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Student) {

            Student student = (Student) authentication.getPrincipal();
            if (!student.getId().equals(reservation.getStudent().getId())) {
                throw new IllegalArgumentException("Only Student Who made the reservation can add a review");
            }

        } else {
            throw new IllegalArgumentException("Only Student can add a review");

        }
        review.setReservation(reservation);
        return reviewRepository.save(review);
    }
}
