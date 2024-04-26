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

    public enum PaymentType { // Перечисление типов платежей
        CASH,
        CREDIT_CARD,
        DEBIT_CARD,
        ONLINE_PAYMENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; // Сумма платежа
    private Date paymentDate;  // Дата платежа

    @Enumerated(EnumType.STRING) // Указывает, что это перечисление
    private PaymentType paymentType; // Тип платежа

    @ManyToOne
    @JoinColumn(name = "booking_id") // Связь с Booking
    private Booking booking;
}
