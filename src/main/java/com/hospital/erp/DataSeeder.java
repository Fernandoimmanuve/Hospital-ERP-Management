package com.hospital.erp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        run();
    }

    public void run() {
        createRoleIfMissing("ADMIN");
        createRoleIfMissing("DOCTOR");
        createRoleIfMissing("PATIENT");
        createRoleIfMissing("RECEPTIONIST");
        createRoleIfMissing("PHARMACIST");
        createRoleIfMissing("LABORATORY");

        createUserIfMissing("admin", "admin123", "ADMIN");
    }

    private void createRoleIfMissing(String roleName) {
        Optional<Role> existingRole = roleRepository.findByName(roleName);
        if (existingRole.isEmpty()) {
            Role role = new Role(roleName);
            roleRepository.save(role);
        }
    }

    private void createUserIfMissing(String username, String password, String roleName) {
        if (userRepository.findByUsername(username).isPresent()) {
            return;
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalStateException("Role not found: " + roleName));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }
}
