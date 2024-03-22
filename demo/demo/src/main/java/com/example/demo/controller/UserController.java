package com.example.demo.controller;


import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller of the User
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id){
        return this.userService.getUserById(id);
    }

    @GetMapping
    public List<User> findAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/findName/{name}")
    public User findUserByName(@PathVariable String name) throws ProductException {
        return userService.getUserByName(name);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User u){
        return userService.createUser(u);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User u){
        return userService.updateUser(u);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

}
