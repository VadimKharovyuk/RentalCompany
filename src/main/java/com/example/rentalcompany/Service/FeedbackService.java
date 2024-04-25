package com.example.rentalcompany.Service;

import com.example.rentalcompany.Model.Feedback;
import com.example.rentalcompany.Repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Feedback not found"));
    }

    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Feedback existingFeedback = getFeedbackById(id);
        existingFeedback.setRating(updatedFeedback.getRating());
        existingFeedback.setComment(updatedFeedback.getComment());
        existingFeedback.setBooking(updatedFeedback.getBooking());
        return feedbackRepository.save(existingFeedback);
    }
    // Дополнительные методы
    public List<Feedback> getFeedbackByRating(int rating) {
        return feedbackRepository.findByRating(rating);
    }

    public List<Feedback> getFeedbackByBooking(Long bookingId) {
        return feedbackRepository.findByBooking_Id(bookingId);
    }
}
