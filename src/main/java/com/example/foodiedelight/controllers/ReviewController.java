package com.example.foodiedelight.controllers;

import com.example.foodiedelight.models.Review;
import com.example.foodiedelight.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/api/businesses/{businessId}/reviews")
    public Review createReview(
            @PathVariable("businessId") String bid,
            @RequestBody Review newReview) {
        newReview.setBusinessId(bid);
        return reviewService.createReview(newReview);
    }

    @GetMapping("/api/businesses/{businessId}/reviews")
    public List<Review> findReviewsForBusiness(
            @PathVariable("businessId") String bid) {
        return reviewService.findReviewsForBusiness(bid);
    }

    @GetMapping("/api/users/{userId}/reviews")
    public List<Review> findReviewsForUsers(
            @PathVariable("userId") int uid) {
        return reviewService.findReviewsForUser(uid);
    }

    @PutMapping("/api/reviews/{reviewId}")
    public int updateReview(
            @PathVariable("reviewId") int rid,
            @RequestBody Review review
    ) {
        return reviewService.updateReview(rid, review);
    }

    @DeleteMapping("/api/reviews/{reviewId}")
    public int deleteReview(
            @PathVariable("reviewId") int rid
    ) {
        return reviewService.deleteReview(rid);
    }

    @PostMapping("/api/reviews")
    public List<Review> findRecentReviews() {
        return reviewService.findRecentReviews();
    }
}
