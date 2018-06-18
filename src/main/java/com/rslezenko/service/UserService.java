package com.rslezenko.service;


import com.rslezenko.model.User;
import com.rslezenko.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    User findByEmail(String email); //email
    User findByUsername(String username);

    User save(UserRegistrationDto registration);

    List<User> findAll();

    User findById(Long id);




}
