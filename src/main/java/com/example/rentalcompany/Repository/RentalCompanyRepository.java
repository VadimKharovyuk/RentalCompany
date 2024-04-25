package com.example.rentalcompany.Repository;

import com.example.rentalcompany.Model.RentalCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalCompanyRepository extends JpaRepository<RentalCompany,Long> {
}
