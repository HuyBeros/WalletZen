package com.walletzen.controller;

import com.walletzen.model.User;
import com.walletzen.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public User register(
            @RequestBody User user
    ){

        return userRepository.save(user);

    }

    @PostMapping("/login")
    public User login(
            @RequestBody User user
    ){

        System.out.println("===== LOGIN REQUEST =====");

        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());

        User foundUser =
                userRepository.findByEmailAndPassword(
                        user.getEmail(),
                        user.getPassword()
                );

        System.out.println("Found User: " + foundUser);

        return foundUser;

    }

}