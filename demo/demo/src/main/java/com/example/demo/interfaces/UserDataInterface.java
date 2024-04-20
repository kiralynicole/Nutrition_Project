package com.example.demo.interfaces;

import com.example.demo.model.User;

import java.util.List;

public interface UserDataInterface {

    /**
     * Retrieves a {@link User} object by its ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The {@link User} object with the specified ID or {@code null} if no user is found.
     */
    User getUserById(int id);

    /**
     * Retrieves a list of all {@link User} objects.
     *
     * @return A {@link List} of {@link User} objects. The list may be empty if no users are found.
     */
    List<User> getAllUsers();

    /**
     * Retrieves a {@link User} object by its name.
     *
     * @param name The name of the user to retrieve.
     * @return The {@link User} object with the specified name or {@code null} if no user is found.
     */
    User getUserByName(String name);

    /**
     * Creates a new {@link User} record.
     *
     * @param u The {@link User} object to create.
     * @return The newly created {@link User} object.
     */
    User createUser(User u);

    /**
     * Deletes a {@link User} record by its ID.
     *
     * @param id The ID of the user to delete.
     * @return A {@link String} message indicating the outcome of the delete operation.
     */
    String deleteUser(int id);

    /**
     * Updates an existing {@link User} record.
     *
     * @param u The {@link User} object to update, containing the updated information.
     * @return The updated {@link User} object.
     */
    User updateUser(User u);

    /**
     * Activates sale mode for all users if initiated by an administrator.
     *
     * @param id The ID of the administrator initiating the sale mode.
     * @throws RuntimeException If the user is not found, is not an administrator, or if the operation fails.
     */
    void addSale(int id);

    /**
     * Deactivates sale mode for all users if initiated by an administrator.
     *
     * @param id The ID of the administrator ending the sale mode.
     * @throws RuntimeException If the user is not found, is not an administrator, or if the operation fails.
     */
    void removeSale(int id);
}
