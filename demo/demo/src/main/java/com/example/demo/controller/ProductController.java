package com.example.demo.controller;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller of the Product
 *
 */

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable int id){
        return this.productService.getProductById(id);
    }

    @GetMapping
    public List<Product> findAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/findName/{name}")
    public Product findProductByName(@PathVariable String name) throws ProductException {
        return productService.getProductByName(name);
    }

    @PostMapping("/addProduct")
        public Product addProduct(@RequestBody Product p){
            return productService.createProduct(p);
        }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product p){
        return productService.updateProduct(p);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }



}
