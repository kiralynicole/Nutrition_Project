package com.example.demo.service;

import com.example.demo.controller.ProductController;
import com.example.demo.exception.InvalidException;
import com.example.demo.interfaces.ProductInterface;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProductService handles the business logic for product operations.
 * It interacts with {@link ProductRepository} to perform CRUD operations on products.
 * This service is used by {@link ProductController} to handle HTTP requests.
 */

@Service
@RequiredArgsConstructor
//implements ProductInterface
public class ProductService  implements ProductInterface {
    private final ProductRepository productRepository;

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to be retrieved.
     * @return The product with the specified ID.
     * @throws RuntimeException If no product is found with the given ID.
     */
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
    }


    /**
     * Retrieves all products available in the repository.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    /**
     * Retrieves a product by its name.
     *
     * @param name The name of the product to be retrieved.
     * @return The product with the specified name.
     * @throws InvalidException If no product exists with the given name.
     */
    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found for name: " + name));
    }

    /**
     * Creates a new product.
     *
     * @param p The product to be created.
     * @return The newly created product.
     */
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }

    /**
     * Updates an existing product.
     *
     * @param p The product with updated information.
     * @return The updated product.
     */
    public Product updateProduct(Product p) {
        Product product = productRepository.findById(p.getId())
                .orElse(null);

        product.setName(p.getName());
        product.setCategory(p.getCategory());
        product.setStock(p.getStock());
        product.setExpirationDate(p.getExpirationDate());
        product.setQuantity(p.getQuantity());
        product.setDescription(p.getDescription());
        product.setImage(p.getImage());
        product.setPrice(p.getPrice());
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted.
     * @return A string message indicating the result of the deletion operation.
     */
    public String deleteProduct(int id){
        productRepository.deleteById(id);
        return "Product with id " + id + " removed";
    }


}
