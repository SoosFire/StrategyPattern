package dk.productapi.service;


import dk.productapi.exception.ValidationException;
import dk.productapi.model.RegisterUserDTO;
import dk.productapi.validation.SimpleValidationStrategy;
import dk.productapi.validation.StrictValidationStrategy;
import dk.productapi.validation.ValidationStrategy;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void register(RegisterUserDTO dto, String mode) {
        ValidationStrategy strategy = getStrategy(mode);
        strategy.validate(dto);
    }

    private ValidationStrategy getStrategy(String mode) {
        return switch (mode) {
            case "strict" -> new StrictValidationStrategy();
            case "simple" -> new SimpleValidationStrategy();
            default -> throw new ValidationException("Ukendt valideringsmode: " + mode);
        };
    }
}
