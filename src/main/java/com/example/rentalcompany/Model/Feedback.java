package com.example.rentalcompany.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating; // Рейтинг (например, 1-5)
    private String comment; // Комментарий

    @ManyToOne
    @JoinColumn(name = "booking_id") // Связь с Booking
    private Booking booking;

    // Getters and Setters
}

