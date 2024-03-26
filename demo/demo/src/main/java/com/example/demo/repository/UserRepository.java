package com.example.demo.repository;


import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The {@code UserRepository} interface extends the {@link JpaRepository} providing CRUD operations
 * and additional queries for {@link User} entities.
 * <p>
 * This repository interface is used to abstract the data layer, enabling simple CRUD operations
 * for {@link User} entities and custom queries such as retrieving a user by name.
 * </p>
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Retrieves a user by their name.
     *
     * @param name The name of the user to find.
     * @return An {@link Optional} containing the found user if present; otherwise, an empty {@link Optional}.
     */
    Optional<User> findByName(String name);

}
