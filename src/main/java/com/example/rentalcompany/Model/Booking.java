package com.example.rentalcompany.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Автоматическое увеличение идентификатора
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;  // Дата окончания бронирования
    private BigDecimal totalCost;  // Общая стоимость бронирования

    @ManyToOne  // Связь многие-к-одному с Car
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne  // Связь многие-к-одному с Customer
    @JoinColumn(name = "customer_id")
    private Customer customer;


}

