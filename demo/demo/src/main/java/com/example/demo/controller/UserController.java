package com.example.demo.controller;


import com.example.demo.exception.InvalidException;
import com.example.demo.interfaces.UserInterface;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to users.
 * It provides endpoints for various operations such as finding a user by ID or name,
 * listing all users, adding a new user, updating an existing user, and deleting a user.
 * This class relies on {@link UserService} for the business logic and operations.
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserInterface userInterface;

    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to be found.
     * @return The user with the specified ID.
     */
    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id){
        return this.userInterface.getUserById(id);
    }


    /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
    @GetMapping
    public List<User> findAllUsers(){
        return userInterface.getAllUsers();
    }


    /**
     * Finds a user by their name.
     *
     * @param name The name of the user to be found.
     * @return The user with the specified name.
     * @throws InvalidException If any error occurs during the operation.
     */
    @GetMapping("/findName/{name}")
    public User findUserByName(@PathVariable String name) throws InvalidException {
        return userInterface.getUserByName(name);
    }

    /**
     * Adds a new user.
     *
     * @param u The user to be added.
     * @return The added user.
     */
    @PostMapping("/addUser")
    public User addUser(@RequestBody User u){
        return userInterface.createUser(u);
    }

    /**
     * Updates an existing user.
     *
     * @param u The user with updated information.
     * @return The updated user.
     */
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User u){
        return userInterface.updateUser(u);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to be deleted.
     * @return A string message indicating the result of the operation.
     */
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){
        return userInterface.deleteUser(id);
    }

}
