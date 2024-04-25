package com.example.rentalcompany.Repository;

import com.example.rentalcompany.Model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
    List<Insurance> findByCar_Id(Long carId);

    List<Insurance> findByBooking_Id(Long bookingId);
}
