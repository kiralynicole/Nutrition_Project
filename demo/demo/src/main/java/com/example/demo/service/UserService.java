package com.example.demo.service;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserByName(String name) throws ProductException {
        return userRepository.getUserByName(name)
                .orElseThrow(() ->new ProductException("The user with name " + name + " doesn t exist"));
    }

    public User createUser(User p) {
        return userRepository.save(p);
    }

    public User updateUser(User u) {
        User user = userRepository.findById(u.getId())
                .orElse(null);

        user.setName(u.getName());
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword());
        user.setAddress(u.getAddress());
        user.setPhone(u.getPhone());
        return userRepository.save(user);
    }

    public String deleteUser(int id){
       userRepository.deleteById(id);
        return "User with id " + id + " removed";
    }

    

}
