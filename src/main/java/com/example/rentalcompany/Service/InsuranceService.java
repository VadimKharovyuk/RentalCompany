package com.example.rentalcompany.Service;

import com.example.rentalcompany.Model.Insurance;
import com.example.rentalcompany.Repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;

    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    public Insurance getInsuranceById(Long id) {
        return insuranceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance not found"));
    }

    public Insurance addInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public void deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
    }

    public Insurance updateInsurance(Long id, Insurance updatedInsurance) {
        Insurance existingInsurance = getInsuranceById(id);
        existingInsurance.setInsuranceType(updatedInsurance.getInsuranceType());
        existingInsurance.setStartDate(updatedInsurance.getStartDate());
        existingInsurance.setEndDate(updatedInsurance.getEndDate());
        existingInsurance.setCost(updatedInsurance.getCost());
        existingInsurance.setBooking(updatedInsurance.getBooking());
        existingInsurance.setCar(updatedInsurance.getCar());
        return insuranceRepository.save(existingInsurance);
    }
    // Дополнительные методы
    public List<Insurance> getInsuranceByCar(Long carId) {
        return insuranceRepository.findByCar_Id(carId);
    }

    public List<Insurance> getInsuranceByBooking(Long bookingId) {
        return insuranceRepository.findByBooking_Id(bookingId);
    }

    public boolean checkInsuranceValidity(Long insuranceId) {
        Insurance insurance = insuranceRepository.findById(insuranceId).orElse(null);
        if (insurance != null) {
            Date today = new Date();
            return today.after(insurance.getStartDate()) && today.before(insurance.getEndDate());
        }
        return false;
    }
}
