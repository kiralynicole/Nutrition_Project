package com.example.demo.service;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductByName(String name) throws ProductException {
        return productRepository.getProductByName(name)
                .orElseThrow(() ->new ProductException("The product with name " + name + " doesn t exist"));
    }

    public Product createProduct(Product p) {
        return productRepository.save(p);
    }

    public Product updateProduct(Product p) {
        Product product = productRepository.findById(p.getId())
                .orElse(null);

        product.setName(p.getName());
        product.setCategory(p.getCategory());
        product.setStock(p.getStock());
        product.setExpirationDate(p.getExpirationDate());
        product.setPrice(p.getPrice());
        return productRepository.save(product);
    }

    public String deleteProduct(int id){
        productRepository.deleteById(id);
        return "Product with id " + id + " removed";
    }
}
