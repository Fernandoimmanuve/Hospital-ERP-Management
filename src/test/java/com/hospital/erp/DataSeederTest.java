package com.hospital.erp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = HospitalerpApplication.class)
class DataSeederTest {

    @Autowired
    private DataSeeder dataSeeder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void seedsAdminAccountOnStartup() {
        dataSeeder.run();

        Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
        User adminUser = userRepository.findByUsername("admin").orElseThrow();

        assertEquals("ADMIN", adminRole.getName());
        assertTrue(passwordEncoder.matches("admin123", adminUser.getPassword()));
        assertEquals(adminRole.getId(), adminUser.getRole().getId());
    }
}
