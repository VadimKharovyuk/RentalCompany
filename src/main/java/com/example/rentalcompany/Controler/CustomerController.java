package com.example.rentalcompany.Controler;

import com.example.rentalcompany.Model.Customer;
import com.example.rentalcompany.Service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer_list"; // Имя шаблона в Thymeleaf
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer); // Передаем объект `Customer` в модель
        return "customer_detail"; // Имя шаблона
    }


//    @PostMapping
//    public String addCustomer(@ModelAttribute Customer customer, Model model) {
//        customerService.addCustomer(customer);
//        return "redirect:/customers"; // Перенаправление после добавления
//    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers"; // Перенаправление после удаления
    }

    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer, Model model) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers"; // Перенаправление после обновления
    }
    // Отображение формы для добавления нового клиента
    @GetMapping("/new")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer()); // Создаем новый объект для формы
        return "customer_form"; // Имя шаблона
    }

    // Отображение формы для редактирования существующего клиента
    @GetMapping("/{id}/edit")
    public String editCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer_form"; // Используем тот же шаблон для редактирования
    }

    // Обработка POST-запроса для добавления или редактирования клиента
    @PostMapping
    public String saveCustomer(@Validated @ModelAttribute Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "customer_form"; // Возвращаемся на форму в случае ошибок валидации
        }

        customerService.saveOrUpdateCustomer(customer); // Сохраняем или обновляем клиента
        return "redirect:/customers"; // Перенаправление к списку клиентов
    }
}
