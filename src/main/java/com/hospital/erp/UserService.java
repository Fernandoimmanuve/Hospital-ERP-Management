package com.hospital.erp;

import java.util.List;

public interface UserService {


    User saveUser(User user);


    List<User> getAllUsers();


    User getUserById(Long id);


    User findByUsername(String username);


    User updateUser(User user);


    void deleteUser(Long id);


    Role getRoleByName(String roleName);


    User registerUser(
            String username,
            String password,
            String roleName
    );

    User registerUser(RegistrationDTO registrationDTO);

}