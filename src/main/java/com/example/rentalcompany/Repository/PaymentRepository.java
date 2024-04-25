package com.example.rentalcompany.Repository;

import com.example.rentalcompany.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByPaymentDateBetween(Date startDate, Date endDate);

    List<Payment> findByBooking_Id(Long bookingId);

}
