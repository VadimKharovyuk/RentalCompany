package com.example.rentalcompany.Repository;

import com.example.rentalcompany.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByCar_Id(Long carId);

    List<Booking> findByStartDateBetween(Date startDate, Date endDate);

    List<Booking> findByCustomer_Id(Long customerId);

}
