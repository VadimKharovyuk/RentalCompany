package com.example.rentalcompany.Service;

import com.example.rentalcompany.Model.Booking;
import com.example.rentalcompany.Model.Insurance;
import com.example.rentalcompany.Repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = getBookingById(id);
        existingBooking.setStartDate(updatedBooking.getStartDate());
        existingBooking.setEndDate(updatedBooking.getEndDate());
        existingBooking.setTotalCost(updatedBooking.getTotalCost());
        existingBooking.setCar(updatedBooking.getCar());
        existingBooking.setCustomer(updatedBooking.getCustomer());
        existingBooking.setInsurance(updatedBooking.getInsurance());
        return bookingRepository.save(existingBooking);
    }

    public List<Booking> getBookingsByDateRange(Date startDate, Date endDate) {
        return bookingRepository.findByStartDateBetween(startDate, endDate);
    }

    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomer_Id(customerId);
    }

    public List<Booking> getBookingsByCar(Long carId) {
        return bookingRepository.findByCar_Id(carId);
    }

    public BigDecimal calculateTotalCost(Booking booking) {
        return booking.getTotalCost();  // Пример расчета общей стоимости
    }

    public Insurance getInsurance(Long bookingId) {
        Booking booking = getBookingById(bookingId);
        return booking.getInsurance();  // Возвращает страховку, связанную с этим бронированием
    }
}
