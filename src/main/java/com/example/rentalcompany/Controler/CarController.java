package com.example.rentalcompany.Controler;
import com.example.rentalcompany.Model.Car;
import com.example.rentalcompany.Service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public String getAllCars(Model model) {
        List<Car> cars = carService.getAllCars(); // Получение списка машин
        model.addAttribute("cars", cars); // Добавление списка в модель
        return "car_list"; // Имя шаблона в `Thymeleaf`
    }


    @GetMapping("/new")
    public String newCar(Model model) {
        model.addAttribute("car", new Car()); // Добавляем новый объект Car
        return "car_form"; // Имя шаблона
    }

    @GetMapping("/{id}")
    public String getCarById(@PathVariable Long id, Model model) {
        System.out.println("Fetching car with id: " + id); // Используйте переменную 'id'

        Car car = carService.getCarById(id); // Вызов правильного метода сервиса
        model.addAttribute("car", car); // Добавление объекта в модель

        return "car_detail"; // Имя шаблона
    }


    @PostMapping
    public String addCar(@Validated Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "car_form";
        }
        carService.addCar(car);
        return "redirect:/cars";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars"; // Перенаправление после удаления
    }


    @PutMapping("/{id}")
    public String updateCar(@PathVariable Long id, Car car, Model model) {
        carService.updateCar(id, car);
        return "redirect:/cars"; // Перенаправление после обновления
    }
}
