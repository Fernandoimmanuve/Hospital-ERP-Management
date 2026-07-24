package com.hospital.erp;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthControllerTest {

    @Test
    void loginPageReturnsLoginView() {
        UserService userService = new UserServiceImpl(null, null, new BCryptPasswordEncoder());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthController controller = new AuthController(userService, passwordEncoder);

        assertEquals("login", controller.loginPage());
    }

    @Test
    void registerPageAddsRegistrationDTOAndReturnsRegisterView() {
        UserService userService = new UserServiceImpl(null, null, new BCryptPasswordEncoder());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthController controller = new AuthController(userService, passwordEncoder);
        Model model = new ExtendedModelMap();

        assertEquals("register", controller.registerPage(model));
        assertTrue(model.containsAttribute("registrationDTO"));
        assertInstanceOf(RegistrationDTO.class, model.getAttribute("registrationDTO"));
    }
}

