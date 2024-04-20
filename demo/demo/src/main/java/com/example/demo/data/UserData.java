package com.example.demo.data;

import com.example.demo.exception.InvalidException;
import com.example.demo.interfaces.UserDataInterface;
import com.example.demo.listeners.EmailMsgListener;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@AllArgsConstructor
@Builder
@Data

public class UserData implements UserDataInterface {

    @Autowired
    private final UserRepository userRepository;

    private Store store;


    /**
     * Retrieves a {@link User} by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return the found {@link User}
     * @throws RuntimeException if no user is found with the provided ID
     */
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for id: " + id));
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

    /**
     * Enables a sale mode for all users if the requesting user is an administrator.
     *
     * @param id the ID of the administrator requesting the sale mode activation
     * @throws RuntimeException if the user does not exist or is not an administrator
     */
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

    /**
     * Disables the sale mode for all users if the requesting user is an administrator.
     *
     * @param id the ID of the administrator requesting to end the sale mode
     * @throws RuntimeException if the user does not exist or is not an administrator
     */
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
