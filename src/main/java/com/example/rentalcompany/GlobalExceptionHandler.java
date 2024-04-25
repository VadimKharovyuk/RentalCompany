package com.example.rentalcompany;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(Model model, IllegalArgumentException ex) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}

