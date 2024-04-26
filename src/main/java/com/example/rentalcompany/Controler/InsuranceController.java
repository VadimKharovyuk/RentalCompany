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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/insurances")
@AllArgsConstructor
public class InsuranceController {
    private final BookingService bookingService;
    private final CarService carService;
    private final CustomerService customerService;
    private final InsuranceService insuranceService;




    @GetMapping
    public String getAllInsurances(Model model) {
        model.addAttribute("insurances", insuranceService.getAllInsurances());
        return "insurances_list";  // Имя шаблона для отображения списка страховок
    }

    @GetMapping("/{id}")
    public String getInsuranceById(@PathVariable Long id, Model model) {
        model.addAttribute("insurance", insuranceService.getInsuranceById(id));
        return "insurance_detail";  // Имя шаблона для отображения деталей страховки
    }

    @GetMapping("/create") // Обрабатывает GET-запрос по маршруту "/create"
    public String createBookingForm(Model model) {
        Booking booking = new Booking(); // Новый объект `Booking`
        Insurance insurance = new Insurance(); // Новый объект `Insurance`
        booking.setInsurance(insurance); // Связываем страховку с бронированием

        List<Car> cars = carService.getAllCars(); // Получаем список машин
        List<Customer> customers = customerService.getAllCustomers(); // Получаем список клиентов

        // Добавляем данные в модель
        model.addAttribute("booking", booking);
        model.addAttribute("cars", cars);
        model.addAttribute("customers", customers);

        // Возвращаем имя шаблона
        return "booking_with_insurance_form"; // Имя HTML-шаблона
    }
    @PostMapping("/create")
    public String createBooking(
            @ModelAttribute("booking") Booking booking,
            RedirectAttributes redirectAttributes
    ) {
        // Установить дату бронирования на текущую дату
        booking.setBookingDate(new Date());

        // Сохранение бронирования
        bookingService.addBooking(booking);

        // Перенаправление с сообщением об успехе
        redirectAttributes.addFlashAttribute("message", "Booking created successfully!");

        return "redirect:/bookings"; // Перенаправление после успешного сохранения
    }


    @PostMapping
    public String saveInsurance(@ModelAttribute Insurance insurance) {
        insuranceService.addInsurance(insurance);
        return "redirect:/insurances";  // Перенаправление на список страховок после сохранения
    }

    @PostMapping("/{id}")
    public String updateInsurance(@PathVariable Long id, @ModelAttribute Insurance updatedInsurance) {
        insuranceService.updateInsurance(id, updatedInsurance);
        return "redirect:/insurances";  // Перенаправление после обновления
    }

    @DeleteMapping("/{id}")
    public String deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
        return "redirect:/insurances";  // Перенаправление после удаления
    }
}
