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
@Table(name = "insurances")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String insuranceType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "car_id")  // Связь с Car
    private Car car;

    @OneToOne(mappedBy = "insurance")  // Связь с Booking
    private Booking booking;

    @ManyToOne(cascade = CascadeType.PERSIST)  // или CascadeType.ALL, если нужно
    private Insurance insurance;

}
