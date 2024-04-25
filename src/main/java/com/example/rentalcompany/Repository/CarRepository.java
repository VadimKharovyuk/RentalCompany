package com.example.rentalcompany.Repository;

import com.example.rentalcompany.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    // Методы для получения автомобилей по разным критериям
    List<Car> findByStatus(String status); // По статусу

    List<Car> findByBrandContainingOrModelContaining(String keyword, String model); // По бренду или модели

    List<Car> findByCompany_Id(Long companyId); // По идентификатору компании



}

