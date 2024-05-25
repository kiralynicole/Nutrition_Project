package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * ProductRepository is an interface that extends JpaRepository, facilitating CRUD operations
 * and additional queries for {@link Product} entities. It interacts with the database to perform
 * operations on the Product table.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Retrieves a product by its name. This method makes use of Spring Data JPA's query derivation mechanism
     * to generate a query from the method name. It searches for a product whose name matches the provided argument.
     *
     * @param name The name of the product to search for.
     * @return An {@link Optional} containing the found product if it exists, or empty if not found.
     */
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Product> getNamesProducts(@Param("search") String search);
}
