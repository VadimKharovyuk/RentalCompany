package com.example.rentalcompany.Service;

import com.example.rentalcompany.Model.Car;
import com.example.rentalcompany.Repository.CarRepository;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(CarService.class);

    public List<Car> getAllCars() {
//        logger.info("Fetching all cars");
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car not found"));
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car existingCar = getCarById(id);
        existingCar.setModel(updatedCar.getModel());
        existingCar.setBrand(updatedCar.getBrand());
        existingCar.setYear(updatedCar.getYear());
        existingCar.setRentalPrice(updatedCar.getRentalPrice());
        existingCar.setStatus(updatedCar.getStatus());
        return carRepository.save(existingCar);
    }

    public List<Car> getCarsByStatus(Car.CarStatus status) { // Корректное использование CarStatus
        return carRepository.findByStatus(status.name());
    }

    public List<Car> getAvailableCars() {
        return carRepository.findByStatus("AVAILABLE");
    }

    public List<Car> searchCarsByBrandOrModel(String keyword) {
        return carRepository.findByBrandContainingOrModelContaining(keyword, keyword);
    }

    public List<Car> getCarsByRentalCompany(Long companyId) {
        return carRepository.findByCompany_Id(companyId);
    }

}
