package dk.productapi.validation;

import dk.productapi.model.RegisterUserDTO;

public interface ValidationStrategy {
    void validate(RegisterUserDTO dto);
}
