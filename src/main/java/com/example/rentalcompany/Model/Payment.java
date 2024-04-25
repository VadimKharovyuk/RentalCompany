package com.example.rentalcompany.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; // Сумма платежа
    private Date paymentDate;  // Дата платежа
    private String paymentType; // Тип платежа (например, кредитная карта, наличные)

    @ManyToOne
    @JoinColumn(name = "booking_id") // Связь с Booking
    private Booking booking;

    // Getters and Setters
}

