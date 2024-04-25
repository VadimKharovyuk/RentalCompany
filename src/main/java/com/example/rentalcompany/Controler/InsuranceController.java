package com.example.rentalcompany.Controler;

import com.example.rentalcompany.Model.Booking;
import com.example.rentalcompany.Model.Insurance;
import com.example.rentalcompany.Service.CarService;
import com.example.rentalcompany.Service.CustomerService;
import com.example.rentalcompany.Service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insurances")
@AllArgsConstructor
public class InsuranceController {
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

    @GetMapping("/create")
    public String createBookingForm(Model model) {
        Booking booking = new Booking();  // Создаем новый объект `Booking`
        Insurance insurance = new Insurance();  // Создаем новый объект `Insurance`
        booking.setInsurance(insurance);  // Добавляем `Insurance` в `Booking`
        model.addAttribute("booking", booking);  // Добавляем объект в модель
        return "booking_with_insurance_form";  // Имя шаблона для формы
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
