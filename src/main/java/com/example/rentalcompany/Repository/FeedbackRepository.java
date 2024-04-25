package com.example.rentalcompany.Repository;

import com.example.rentalcompany.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByRating(int rating);

    List<Feedback> findByBooking_Id(Long bookingId);

}
