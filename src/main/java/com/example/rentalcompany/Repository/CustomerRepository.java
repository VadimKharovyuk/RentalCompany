package com.example.rentalcompany.Repository;

import com.example.rentalcompany.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByNameContaining(String name);

    List<Customer> findByContactInfoContaining(String contactInfo);

}
