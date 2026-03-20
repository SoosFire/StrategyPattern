package dk.productapi.validation;

import dk.productapi.exception.ValidationException;
import dk.productapi.model.RegisterUserDTO;
import org.springframework.stereotype.Component;

@Component
public class StrictValidationStrategy implements ValidationStrategy {

    @Override
    public void validate(RegisterUserDTO dto) {
        if (dto.getUsername() == null || dto.getUsername().length() < 6) {
            throw new ValidationException("Username skal være mindst 6 tegn");
        }
        if (dto.getUsername().contains(" ")) {
            throw new ValidationException("Username må ikke indeholde mellemrum");
        }
        if (dto.getEmail() == null || !dto.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new ValidationException("Email skal være en gyldig email-adresse");
        }
        if (dto.getPassword() == null || dto.getPassword().length() < 10) {
            throw new ValidationException("Password skal være mindst 10 tegn");
        }
        if (!dto.getPassword().matches(".*[a-zA-Z].*") || !dto.getPassword().matches(".*\\d.*")) {
            throw new ValidationException("Password skal indeholde både bogstaver og tal");
        }
    }
}
