package com.example.foodiedelight.services;

import com.example.foodiedelight.models.Review;
import com.example.foodiedelight.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
  @Autowired
  ReviewRepository reviewRepository;

  public Review createReview(Review newReview) {
    return reviewRepository.save(newReview);
  }

  public List<Review> findReviewsForBusiness(String bid) {
    return reviewRepository.findReviewsForBusiness(bid);
  }

  public List<Review> findReviewsForUser(int uid) {
    return reviewRepository.findReviewsForUser(uid);
  }

  public int updateReview(int rid, Review review) {
    Review oldReview = reviewRepository.findReviewById(rid);
    oldReview.setRating(review.getRating());
    oldReview.setContent(review.getContent());
    reviewRepository.save(oldReview);
    return 1;
  }

  public int deleteReview(int rid) {
    reviewRepository.deleteById(rid);
    return 1;
  }

  public List<Review> findRecentReviews() {
    return reviewRepository.findRecentReviews();
  }
}
