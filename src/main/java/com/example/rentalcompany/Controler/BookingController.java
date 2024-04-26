package com.example.rentalcompany.Controler;

import com.example.rentalcompany.Model.Booking;
import com.example.rentalcompany.Model.Car;
import com.example.rentalcompany.Model.Customer;
import com.example.rentalcompany.Model.Insurance;
import com.example.rentalcompany.Service.BookingService;

import com.example.rentalcompany.Service.CarService;
import com.example.rentalcompany.Service.CustomerService;
import com.example.rentalcompany.Service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final CarService carService;
    private final CustomerService customerService;
    private final InsuranceService insuranceService;

    @GetMapping
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "booking_list";  // Имя шаблона в `Thymeleaf`
    }


    @GetMapping("/{id}")
    public String getBookingById(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "booking_detail";  // Имя шаблона
    }

//    @GetMapping("/create")
//    public String createBookingForm(Model model) {
//        Booking newBooking = new Booking();
//
//        // Получение всех существующих машин и клиентов
//        List<Car> cars = carService.getAllCars();
//        List<Customer> customers = customerService.getAllCustomers();
//        Booking booking = new Booking();
//        Insurance insurance = new Insurance();
//        booking.setInsurance(insurance);
//
//        // Добавляем список значений для выпадающего списка
//        List<String> bookingNames = Arrays.asList("Business", "Vacation", "Family");  // Пример значений
//        model.addAttribute("bookingNames", bookingNames);
//
//        model.addAttribute("booking", booking);
//
//        model.addAttribute("booking", newBooking);
//        model.addAttribute("cars", cars); // Список всех машин
//        model.addAttribute("customers", customers); // Список всех клиентов
//
//        return "insurance_form";  // Имя шаблона для формы создания
//    }
    @GetMapping("/create")
    public String createBookingForm(Model model) {
        Booking booking = new Booking();  // Новый объект бронирования
        booking.setInsurance(new Insurance());  // Добавляем страховку

        List<String> bookingNames = Arrays.asList("Business", "Vacation", "Family");  // Пример возможных значений для имени бронирования
        List<Car> cars = carService.getAllCars();  // Получаем список машин
        List<Customer> customers = customerService.getAllCustomers();  // Получаем список клиентов

        // Добавляем объекты в модель
        model.addAttribute("booking", booking);
        model.addAttribute("cars", cars);
        model.addAttribute("customers", customers);
        model.addAttribute("bookingNames", bookingNames);

        return "insurance_form";  // Имя шаблона для формы
    }


    @PostMapping
    public String saveBooking(@ModelAttribute("booking") Booking booking, RedirectAttributes redirectAttributes) {
        // Save the booking first
        Booking newBooking = bookingService.addBooking(booking);

        // Check if insurance exists and save it
        Insurance insurance = booking.getInsurance();
        if (insurance != null) {
            insuranceService.addInsurance(insurance);  // Save the insurance
            insurance.setBooking(newBooking);  // Set the booking reference
        }

        // Add a flash message for successful creation
        redirectAttributes.addFlashAttribute("message", "Booking created successfully!");

        // Redirect to the booking list
        return "redirect:/bookings";
    }




    @GetMapping("/{id}/edit")
    public String editBookingForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "booking_form";  // Используем ту же форму для редактирования
    }

    @PostMapping("/{id}")
    public String updateBooking(@PathVariable Long id, @ModelAttribute("booking") Booking updatedBooking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "booking_form";  // Вернуть форму при ошибках
        }
        bookingService.updateBooking(id, updatedBooking);
        return "redirect:/bookings";  // Перенаправление после обновления
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";  // Перенаправление после удаления
    }
    @PostMapping("/create")
    public String createBooking(@ModelAttribute("booking") Booking booking) {
        bookingService.addBooking(booking);  // Сохраняем бронирование
        if (booking.getInsurance() != null) {
            booking.getInsurance().setBooking(booking);  // Связь страховки с бронированием
        }

        return "redirect:/bookings";  // Перенаправляем после сохранения
    }
}
