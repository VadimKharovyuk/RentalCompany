package com.example.rentalcompany.Controler;

import com.example.rentalcompany.Model.Car;
import com.example.rentalcompany.Model.Booking;
import com.example.rentalcompany.Model.Customer;
import com.example.rentalcompany.Model.Payment;
import com.example.rentalcompany.Service.CarService;
import com.example.rentalcompany.Service.BookingService;
import com.example.rentalcompany.Service.CustomerService;
import com.example.rentalcompany.Service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final CarService carService;
    private final BookingService bookingService;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    @GetMapping("/")
    public String home(Model model) {
        List<Car> cars = carService.getAllCars();
        List<Booking> bookings = bookingService.getAllBookings();
        List<Customer> customers = customerService.getAllCustomers();
        List<Payment> payments = paymentService.getAllPayments();

        model.addAttribute("cars", cars);
        model.addAttribute("bookings", bookings);
        model.addAttribute("customers", customers);
        model.addAttribute("payments", payments);

        return "home"; // это имя HTML-шаблона для домашней страницы
    }
}