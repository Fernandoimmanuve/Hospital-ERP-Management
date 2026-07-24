package com.hospital.erp;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());

        List<String> bloodGroups = Arrays.asList("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        model.addAttribute("bloodGroups", bloodGroups);

        List<String> roles = Arrays.asList("PATIENT", "DOCTOR", "RECEPTIONIST", "PHARMACIST", "LABORATORY", "ADMIN");
        model.addAttribute("availableRoles", roles);

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check if passwords match
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }

        // Check if username already exists
        try {
            userService.findByUsername(registrationDTO.getUsername());
            bindingResult.rejectValue("username", "error.username", "Username is already taken");
        } catch (RuntimeException e) {
            // Username not found - this is good (it means it's available)
        }

        if (bindingResult.hasErrors()) {
            List<String> bloodGroups = Arrays.asList("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
            model.addAttribute("bloodGroups", bloodGroups);

            List<String> roles = Arrays.asList("PATIENT", "DOCTOR", "RECEPTIONIST", "PHARMACIST", "LABORATORY", "ADMIN");
            model.addAttribute("availableRoles", roles);

            return "register";
        }

        try {
            // Register the user with all details
            userService.registerUser(registrationDTO);

            // Redirect to login with success message
            redirectAttributes.addFlashAttribute("registrationSuccess", true);
            return "redirect:/login?success=true";
        } catch (Exception e) {
            List<String> bloodGroups = Arrays.asList("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
            model.addAttribute("bloodGroups", bloodGroups);

            List<String> roles = Arrays.asList("PATIENT", "DOCTOR", "RECEPTIONIST", "PHARMACIST", "LABORATORY", "ADMIN");
            model.addAttribute("availableRoles", roles);

            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage(Model model) {
        model.addAttribute("email", "");
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(
            @ModelAttribute("email") String email,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (email == null || email.trim().isEmpty()) {
            model.addAttribute("error", "Please enter your email address");
            return "forgot-password";
        }

        // In a real application, this would send an email with a password reset link
        // For now, we just show a success message
        redirectAttributes.addFlashAttribute("resetEmailSent", true);
        return "redirect:/login?reset=true";
    }
}

