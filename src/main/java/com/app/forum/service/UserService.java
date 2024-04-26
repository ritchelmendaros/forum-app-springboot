package com.app.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.forum.model.User;
import com.app.forum.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    public User findByFirstnameAndLastname(String firstname, String lastname) {
        for (User user : userRepository.findAll()) {
            if (user.getFirstname().equals(firstname) && user.getLastname().equals(lastname)) {
                return user;
            }
        }
        return null;
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public boolean existsById(Integer userId) {
        return userRepository.existsById(userId);
    }
}
