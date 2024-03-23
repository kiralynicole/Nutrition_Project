package com.example.demo.controller;

import com.example.demo.exception.InvalidException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController is responsible for handling HTTP requests related to products.
 * It offers endpoints for various operations such as finding a product by ID or name,
 * listing all products, adding a new product, updating an existing product, and deleting a product.
 * This controller utilizes {@link ProductService} to perform the underlying business logic and operations.
 */

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;

    /**
     * Finds a product by its ID.
     *
     * @param id The ID of the product to be found.
     * @return The product with the specified ID.
     */
    @GetMapping("/{id}")
    public Product findProductById(@PathVariable int id){
        return this.productService.getProductById(id);
    }

    /**
     * Retrieves a list of all products.
     *
     * @return A list of all products.
     */
    @GetMapping
    public List<Product> findAllProducts(){
        return productService.getAllProducts();
    }

    /**
     * Finds a product by its name.
     *
     * @param name The name of the product to be found.
     * @return The product with the specified name.
     * @throws InvalidException If any error occurs during the operation.
     */
    @GetMapping("/findName/{name}")
    public Product findProductByName(@PathVariable String name) throws InvalidException {
        return productService.getProductByName(name);
    }

    /**
     * Adds a new product.
     *
     * @param p The product to be added.
     * @return The added product.
     */
    @PostMapping("/addProduct")
        public Product addProduct(@RequestBody Product p){
            return productService.createProduct(p);
        }

    /**
     * Updates an existing product.
     *
     * @param p The product with updated information.
     * @return The updated product.
     */
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product p){
        return productService.updateProduct(p);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted.
     * @return A string message indicating the result of the operation.
     */
    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }



}
