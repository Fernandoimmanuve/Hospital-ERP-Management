package com.hospital.erp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            Role role = roleRepository.findByName("PATIENT")
                    .orElseThrow(() -> new RuntimeException("Default role PATIENT not found"));
            user.setRole(role);
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found : " + username));
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public User registerUser(String username, String password, String roleName) {
        Role role = getRoleByName(roleName);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User registerUser(RegistrationDTO dto) {
        // Determine role
        String roleName = dto.getRole();
        if (roleName == null || roleName.isEmpty()) {
            roleName = "PATIENT";
        }
        Role role = getRoleByName(roleName.toUpperCase());

        // Create and populate user
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setMobileNumber(dto.getMobileNumber());
        user.setGender(dto.getGender());

        // Parse date of birth
        if (dto.getDateOfBirth() != null && !dto.getDateOfBirth().isEmpty()) {
            user.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        }

        user.setBloodGroup(dto.getBloodGroup());
        user.setAddress(dto.getAddress());
        user.setEnabled(true);

        return userRepository.save(user);
    }
}
