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
@Table(name = "payments") // Название таблицы в базе данных
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическое увеличение идентификатора
    private Long id;

    private BigDecimal amount; // Сумма платежа
    private Date paymentDate;  // Дата платежа
    private String paymentType; // Тип платежа (например, кредитная карта, наличные)

    @ManyToOne(fetch = FetchType.LAZY) // Связь с `Booking`
    @JoinColumn(name = "booking_id") // Указание столбца, по которому происходит связь
    private Booking booking;

    // Конструкторы, геттеры и сеттеры уже созданы благодаря аннотациям Lombok
}
