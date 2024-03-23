package com.example.demo.repository;


import com.example.demo.model.Order;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * The {@code OrderRepository} interface extends the {@link JpaRepository} interface,
 * providing CRUD operations for {@link Order} entities within the database.
 * <p>
 * This repository manages the data layer for {@link Order} entities, enabling basic CRUD operations
 * such as save, delete, and find by ID, among others, as defined in the {@link JpaRepository}. Custom
 * methods for more specific queries related to {@code Order} entities can be added here.
 * </p>
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {


}
