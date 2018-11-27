package com.blue.iotapp.controller;

import com.blue.iotapp.model.User;
import com.blue.iotapp.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) throws UserPrincipalNotFoundException {
                if (!userRepository.findById(id).isPresent()) {
                    throw new UserPrincipalNotFoundException("id-" + id);
                }
        return userRepository.findById(id).get();
    }
}
