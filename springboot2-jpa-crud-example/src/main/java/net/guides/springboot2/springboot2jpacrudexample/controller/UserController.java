package net.guides.springboot2.springboot2jpacrudexample.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.User;
import net.guides.springboot2.springboot2jpacrudexample.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String getCountry(@RequestParam String name) {
        List<User> users = userRepository.findAll();
        User user = null;
        for (User usr : users) {
            if(usr.getName().toLowerCase().equals(name.toLowerCase())) {
                user = usr;
                break;
            }
        }

        if(user == null) return null;
        return user.getCountry();
    }
}
