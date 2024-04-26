package com.example.rentalcompany.Controler;
import com.example.rentalcompany.Model.Booking;
import com.example.rentalcompany.Model.Payment;
import com.example.rentalcompany.Service.BookingService;
import com.example.rentalcompany.Service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    private final BookingService bookingService; // Сервис бронирований

    @GetMapping
    public String getAllPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "payment_list";  // Имя шаблона, который отображает список всех платежей
    }

    @GetMapping("/{id}")
    public String getPaymentById(@PathVariable Long id, Model model) {
        Payment payment = paymentService.getPaymentById(id);
        model.addAttribute("payment", payment);
        return "payment_detail";  // Имя шаблона для деталей платежа
    }

    @GetMapping("/create")
    public String createPaymentForm(Model model) {
        List<Booking> bookings = bookingService.getAllBookings(); // Получаем список всех бронирований
        model.addAttribute("bookings", bookings); // Добавляем в модель

        // Список типов платежей
        List<Payment.PaymentType> paymentTypes = Arrays.asList(Payment.PaymentType.values());
        model.addAttribute("paymentTypes", paymentTypes); // Добавляем в модель

        model.addAttribute("payment", new Payment()); // Новый пустой объект для формы
        return "payment_form"; // Имя шаблона для формы создания платежа
    }

    @PostMapping
    public String addPayment(@ModelAttribute("payment") Payment payment, RedirectAttributes redirectAttributes) {
        paymentService.addPayment(payment); // Добавление нового платежа
        redirectAttributes.addFlashAttribute("successMessage", "Payment created successfully!");
        return "redirect:/payments"; // После создания перенаправление на список платежей
    }

    @GetMapping("/{id}/edit")
    public String editPaymentForm(@PathVariable Long id, Model model) {
        Payment payment = paymentService.getPaymentById(id);
        model.addAttribute("payment", payment);
        return "payment_form"; // Используем тот же шаблон, что и для создания
    }

    @PostMapping("/{id}")
    public String updatePayment(@PathVariable Long id, @ModelAttribute Payment updatedPayment, RedirectAttributes redirectAttributes) {
        Payment updated = paymentService.updatePayment(id, updatedPayment);
        redirectAttributes.addFlashAttribute("successMessage", "Payment updated successfully!");
        return "redirect:/payments"; // После обновления перенаправление на список платежей
    }

    @PostMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        paymentService.deletePayment(id);
        redirectAttributes.addFlashAttribute("successMessage", "Payment deleted successfully!");
        return "redirect:/payments"; // После удаления перенаправление на список платежей
    }
}
