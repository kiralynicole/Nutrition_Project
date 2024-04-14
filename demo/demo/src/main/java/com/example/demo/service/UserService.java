package com.example.demo.service;

import com.example.demo.exception.InvalidException;
import com.example.demo.interfaces.UserInterface;
import com.example.demo.listeners.EmailMsgListener;
import com.example.demo.model.Product;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The {@code UserService} class provides services for managing {@link User} entities.
 * It includes methods for CRUD operations and user-specific queries, such as retrieving a user by name.
 * This service relies on {@link UserRepository} for persistence operations.
 *
 * @author Your Name or Team
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {

    private final UserRepository userRepository;
    private Store store = new Store();

    /**
     * Retrieves a {@link User} by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return the found {@link User}
     * @throws RuntimeException if no user is found with the provided ID
     */
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
    }

    /**
     * Retrieves all users in the system.
     *
     * @return a list of {@link User}
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a {@link User} by their name.
     *
     * @param name the name of the user to retrieve
     * @return the found {@link User}
     * @throws InvalidException if no user exists with the specified name
     */
    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found for name: " + name));
    }

    /**
     * Creates a new {@link User} in the system.
     *
     * @param u the {@link User} to create
     * @return the created {@link User}
     */
    public User createUser(User u) {
        return userRepository.save(u);
    }


    /**
     * Updates an existing {@link User}.
     *
     * Note: This method assumes the user with the given ID already exists.
     *
     * @param u the {@link User} to update
     * @return the updated {@link User}, or null if the user does not exist
     */
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

    /**
     * Deletes a {@link User} by their ID.
     *
     * @param id the ID of the user to delete
     * @return a confirmation message indicating successful deletion
     */
    public String deleteUser(int id){
       userRepository.deleteById(id);
        return "User with id " + id + " removed";
    }

    public void addSale(int id){
        User admin = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for id: " + id));

        if(admin.isAdmin()){
            admin.setSale(true);
            userRepository.save(admin);
            List<User> users = getAllUsers();

            for(User u: users){
                u.setSale(true);
                userRepository.save(u);
                store.getNotificationService().subscribe(new EmailMsgListener(u.getEmail()));
            }
            store.newSale();
        }


    }

    public void removeSale(int id){
        User admin = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for id: " + id));

        if(admin.isAdmin()){
            admin.setSale(false);
            userRepository.save(admin);
            List<User> users = getAllUsers();

            for(User u: users){
                u.setSale(false);
                userRepository.save(u);
                store.getNotificationService().subscribe(new EmailMsgListener(u.getEmail()));
            }
            store.newSale();
        }


    }

    

}
