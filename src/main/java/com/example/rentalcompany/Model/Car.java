package com.example.rentalcompany.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String brand;
    private int year;
    private BigDecimal rentalPrice;

    private String status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private RentalCompany company;

    public enum CarStatus {
        AVAILABLE,
        RENTED,
        UNDER_MAINTENANCE,
        SOLD
    }
}

