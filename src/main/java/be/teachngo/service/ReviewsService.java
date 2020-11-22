package be.teachngo.service;

import be.teachngo.data.Review;

import java.util.List;

public interface ReviewsService {
    List<Review> getAllReviewsOfTeacher(Long id);

    List<Review> getAllReviewsOfStudent(Long id);

    Review save(Review review);
}
