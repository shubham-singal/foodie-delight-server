package com.example.foodiedelight.repositories;

import com.example.foodiedelight.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewRepository  extends CrudRepository<Review, Integer> {
  @Query("SELECT review from Review review WHERE review.businessId=:businessId")
  public List<Review> findReviewsForBusiness(@Param("businessId") String bid);

  @Query("SELECT review from Review review WHERE review.userId=:userId")
  public List<Review> findReviewsForUser(@Param("userId") int uid);

  @Query("SELECT review from Review review WHERE review.id=:reviewId")
  public Review findReviewById(@Param("reviewId") int rid);

  @Query(value="SELECT * FROM reviews ORDER BY reviews.id DESC LIMIT 8", nativeQuery=true)
  public List<Review> findRecentReviews();
}

