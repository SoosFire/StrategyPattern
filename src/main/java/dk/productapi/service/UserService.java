package dk.productapi.service;


import dk.productapi.exception.ValidationException;
import dk.productapi.model.RegisterUserDTO;
import dk.productapi.model.ValidationMode;
import dk.productapi.validation.SimpleValidationStrategy;
import dk.productapi.validation.StrictValidationStrategy;
import dk.productapi.validation.ValidationStrategy;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final SimpleValidationStrategy simple;
    private final StrictValidationStrategy strict;

    public UserService(SimpleValidationStrategy simple, StrictValidationStrategy strict) {
        this.simple = simple;
        this.strict = strict;
    }

    public void register(RegisterUserDTO dto, ValidationMode mode) {
        ValidationStrategy strategy = getStrategy(mode);
        strategy.validate(dto);
    }

    private ValidationStrategy getStrategy(ValidationMode mode) {
        return switch (mode) {
            // Ja forstår godt return switch.
            // For x case returnér y værdi, vi bruger ikke default da vi sikre gennem compiler og ikke Runtime
            // eftersom vi bruger Enums og ikke strings.
            case STRICT -> strict;
            case SIMPLE -> simple;
        };
    }
}
