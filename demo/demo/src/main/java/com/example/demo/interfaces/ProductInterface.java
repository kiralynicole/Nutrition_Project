package com.example.demo.interfaces;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * The {@code ProductInterface} defines the operations that can be performed on {@link Product} entities.
 * It includes basic CRUD operations and the ability to fetch products by specific criteria, such as their name.
 */
public interface ProductInterface {
    /**
     * Retrieves all products.
     *
     * @return a list of all {@link Product} entities
     */
    List<Product> findAll();

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to find
     * @return the found {@link Product}, or {@code null} if no product is found with the provided ID
     */
    Product findById(int id);

    /**
     * Saves a given product, either creating a new entry or updating an existing one.
     *
     * @param p the {@link Product} to save
     */
    void save(Product p);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    void deleteById(int id);

    /**
     * Retrieves a product by its name.
     *
     * @param name the name of the product to find
     * @return an {@link Optional} describing the found {@link Product}, or an empty {@link Optional} if no product is found
     */
    Optional<Product> getProductByName(String name);

    /**
     * Creates a new product.
     *
     * @param p the {@link Product} to create
     * @return the created {@link Product}
     */
    Product createProduct(Product p);


    /**
     * Updates an existing product.
     *
     * @param p the {@link Product} to update
     * @return the updated {@link Product}
     */
    Product updateProduct(Product p);
}
