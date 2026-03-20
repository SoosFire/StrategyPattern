package dk.productapi.controller;

import dk.productapi.model.RegisterUserDTO;
import dk.productapi.model.ValidationMode;
import dk.productapi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new RegisterUserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterUserDTO dto,
            @RequestParam(defaultValue = "simple") ValidationMode mode) {
        userService.register(dto,mode);
        return "success";
    }
}