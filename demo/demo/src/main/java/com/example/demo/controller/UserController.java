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
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/findEmail/{email}")
    public User findUserByEmail(@PathVariable String email) throws InvalidException {
        return userInterface.getUserByEmail(email);
    }

    @GetMapping("/login/{email}/{password}")
    public User findUserAtLogin(@PathVariable String email,@PathVariable String password) throws InvalidException{
        return userInterface.getUserLogin(email,password);
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

    /**
     * Endpoint to activate sale mode for all users, initiated by an administrator.
     *
     * @param id The ID of the administrator initiating the sale mode.
     * @throws RuntimeException If the initiating user is not an administrator or does not exist.
     */
    @PostMapping("/addSale/{id}/{discount}")
    public void addSale(@PathVariable int id, @PathVariable int discount){
        userInterface.addSale(id, discount);
    }

    /**
     * Endpoint to deactivate sale mode for all users, initiated by an administrator.
     *
     * @param id The ID of the administrator ending the sale mode.
     * @throws RuntimeException If the initiating user is not an administrator or does not exist.
     */
    @PostMapping("/removeSale/{id}")
    public void removeSale(@PathVariable int id){
        userInterface.removeSale(id);
    }
}
