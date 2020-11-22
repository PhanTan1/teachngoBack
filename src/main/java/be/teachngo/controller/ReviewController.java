package be.teachngo.controller;

import be.teachngo.data.Review;
import be.teachngo.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class ReviewController {


    @Autowired
    private ReviewsService reviewsService;

    // affiché toutes les reviews pour teacher
    @GetMapping("/reviews/byteacher/{id}")
    List<Review> getAllReviewsByTeacher(@PathVariable Long id) {
        return reviewsService.getAllReviewsOfTeacher(id);
    }

    // affiché toutes les reviews pour student
    @GetMapping("/reviews/bystudent/{id}")
    List<Review> getAllReviewsByStudent(@PathVariable Long id) {
        return reviewsService.getAllReviewsOfStudent(id);
    }


    // add review pour une reservation
    @PostMapping("/reviews")
    Review newAvailability(@RequestBody Review review) {


        return reviewsService.save(review);
    }


}
