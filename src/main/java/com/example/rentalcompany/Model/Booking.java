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
    private Date endDate;

    private BigDecimal totalCost;

    private String bookingName;
    private Date bookingDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer_booking", foreignKeyDefinition = "ON DELETE CASCADE"))
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL) // Каскадное удаление при удалении Booking
    @JoinColumn(name = "insurance_id", foreignKey = @ForeignKey(name = "fk_insurance_booking", foreignKeyDefinition = "ON DELETE CASCADE"))
    private Insurance insurance;

}
