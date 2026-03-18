package dk.productapi;

import dk.productapi.exception.ValidationException;
import dk.productapi.model.RegisterUserDTO;
import dk.productapi.validation.SimpleValidationStrategy;
import dk.productapi.validation.StrictValidationStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationStrategyTest {

    // ===== SimpleValidationStrategy =====

    @Test
    void simple_validInput_doesNotThrow() {
        SimpleValidationStrategy strategy = new SimpleValidationStrategy();
        RegisterUserDTO dto = createDto("jonas", "jonas@test.dk", "123456");

        assertDoesNotThrow(() -> strategy.validate(dto));
    }

    @Test
    void simple_shortUsername_throwsException() {
        SimpleValidationStrategy strategy = new SimpleValidationStrategy();
        RegisterUserDTO dto = createDto("jo", "jonas@test.dk", "123456");

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> strategy.validate(dto)
        );
        assertTrue(ex.getMessage().contains("3 tegn"));
    }

    // ===== StrictValidationStrategy =====

    @Test
    void strict_validInput_doesNotThrow() {
        StrictValidationStrategy strategy = new StrictValidationStrategy();
        RegisterUserDTO dto = createDto("jonashansen", "jonas@test.dk", "password123");

        assertDoesNotThrow(() -> strategy.validate(dto));
    }

    @Test
    void strict_passwordWithoutDigits_throwsException() {
        StrictValidationStrategy strategy = new StrictValidationStrategy();
        RegisterUserDTO dto = createDto("jonashansen", "jonas@test.dk", "passwordonly");

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> strategy.validate(dto)
        );
        assertTrue(ex.getMessage().contains("bogstaver og tal"));
    }

    // ===== Helper =====

    private RegisterUserDTO createDto(String username, String email, String password) {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername(username);
        dto.setEmail(email);
        dto.setPassword(password);
        return dto;
    }
}