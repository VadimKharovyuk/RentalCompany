package com.example.rentalcompany.Service;

import com.example.rentalcompany.Model.Booking;
import com.example.rentalcompany.Model.Customer;
import com.example.rentalcompany.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setContactInfo(updatedCustomer.getContactInfo());
        return customerRepository.save(existingCustomer);
    }
    // Дополнительные методы
    public List<Customer> findCustomersByName(String name) {
        return customerRepository.findByNameContaining(name);
    }

    public List<Customer> findCustomersByContactInfo(String contactInfo) {
        return customerRepository.findByContactInfoContaining(contactInfo);
    }

    public List<Booking> getCustomerBookings(Long customerId) {
        return customerRepository.findById(customerId).get().getBookings();
    }
}
