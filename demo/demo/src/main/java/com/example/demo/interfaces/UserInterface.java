package com.example.demo.interfaces;

import com.example.demo.model.Product;
import com.example.demo.model.User;

import java.util.List;

public interface UserInterface {

    User getUserById(int id);
    List<User> getAllUsers();
    User getUserByName(String name);

    User createUser(User u);
    String deleteUser(int id);
    User updateUser(User u);

}
