package com.example.rentalcompany.Service;

import com.example.rentalcompany.Model.Payment;
import com.example.rentalcompany.Repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            payment.setAmount(updatedPayment.getAmount());
            payment.setPaymentDate(updatedPayment.getPaymentDate());
            payment.setPaymentType(updatedPayment.getPaymentType());
            return paymentRepository.save(payment);
        }
        return null;
    }

    // Дополнительные методы
    public List<Payment> getPaymentsByDateRange(Date startDate, Date endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate);
    }

    public List<Payment> getPaymentsByBooking(Long bookingId) {
        return paymentRepository.findByBooking_Id(bookingId);
    }

    public BigDecimal getTotalRevenue(Date startDate, Date endDate) {
        // Пример расчета общего дохода за определенный период
        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startDate, endDate);
        return payments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
