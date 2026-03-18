package dk.productapi.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidation(ValidationException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
