package dk.productapi.validation;

import dk.productapi.exception.ValidationException;
import dk.productapi.model.RegisterUserDTO;
import org.springframework.stereotype.Component;

@Component
public class SimpleValidationStrategy implements ValidationStrategy {


    @Override
    public void validate(RegisterUserDTO dto) {
        if (dto.getUsername() == null || dto.getUsername().length() < 3) {
            throw new ValidationException("Username skal være mindst 3 tegn");
        }
        if (dto.getEmail() == null || !dto.getEmail().contains("@")) {
            throw new ValidationException("Email skal indeholde @");
        }
        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new ValidationException("Password skal være mindst 6 tegn");
        }
    }
}
