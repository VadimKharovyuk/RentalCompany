package com.example.rentalcompany.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Первичный ключ с автоувеличением

    private String name;  // Имя клиента
    private String contactInfo;  // Контактная информация (email, телефон и т.д.)

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)  // Одно-ко-многим с Booking
    private List<Booking> bookings;
}

