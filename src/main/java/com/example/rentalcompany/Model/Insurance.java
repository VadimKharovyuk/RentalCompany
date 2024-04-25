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
@Table(name = "insurances")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String insuranceType; // Тип страховки (например, стандартная, полная)
    private Date startDate; // Дата начала страховки
    private Date endDate; // Дата окончания страховки
    private BigDecimal cost; // Стоимость страховки

    @ManyToOne
    @JoinColumn(name = "car_id") // Связь с Car
    private Car car;

    @ManyToOne
    @JoinColumn(name = "booking_id") // Связь с Booking
    private Booking booking;

    // Getters and Setters
}

