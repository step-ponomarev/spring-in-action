package tacos.web.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tacos.order.data.TacoOrder;
import tacos.security.RegistrationForm;
import tacos.security.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @ModelAttribute("registerForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @PostMapping
    public String processRegistration(Model model, @Valid @ModelAttribute(name = "registerForm") RegistrationForm form, Errors errors) {
        if (errors.hasErrors()) {
            return registerForm();
        }

        if (!Objects.equals(form.getPassword(), form.getConfirmPassword())) {
            errors.rejectValue("password", "passwordsDoNotMatch");
            errors.rejectValue("confirmPassword", "passwordsDoNotMatch");
            return registerForm();
        }

        userService.save(form.toUser(passwordEncoder));

        return "redirect:/login";
    }
}
