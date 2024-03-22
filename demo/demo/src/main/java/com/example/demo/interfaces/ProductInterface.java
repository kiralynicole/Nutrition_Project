package com.example.demo.interfaces;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductInterface {
    List<Product> findAll();
    Product findById(int id);

    void save(Product p);

    void deleteById(int id);
    Optional<Product> getProductByName(String name);
    Product createProduct(Product p);
    Product updateProduct(Product p);
}
