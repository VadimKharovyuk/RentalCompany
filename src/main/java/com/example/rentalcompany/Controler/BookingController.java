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

    @GetMapping("/create")
    public String createBookingForm(Model model) {
        Booking newBooking = new Booking();

        // Получение всех существующих машин и клиентов
        List<Car> cars = carService.getAllCars();
        List<Customer> customers = customerService.getAllCustomers();

        model.addAttribute("booking", newBooking);
        model.addAttribute("cars", cars); // Список всех машин
        model.addAttribute("customers", customers); // Список всех клиентов

        return "booking_form";  // Имя шаблона для формы создания
    }


    // Обработка POST-запроса для сохранения нового бронирования
    @PostMapping
    public String saveBooking(@ModelAttribute("booking") Booking booking, RedirectAttributes redirectAttributes) {
        // Сохранение бронирования
        Booking newBooking = bookingService.addBooking(booking);

        // Если есть страховка, связываем с новым бронированием и сохраняем
        Insurance insurance = booking.getInsurance();
        if (insurance != null) {
            insurance.setBooking(newBooking); // Связь страховки с новым бронированием
            insuranceService.addInsurance(insurance); // Сохранение страховки
        }

        // Добавление сообщения об успешном сохранении
        redirectAttributes.addFlashAttribute("message", "Booking created successfully!");

        // Перенаправление на список бронирований
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
}
