package com.example.demo.product;

import com.example.demo.interfaces.ProductInterface;
import com.example.demo.interfaces.ProductInterface;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Tests for the {@link ProductService} class.
 */
@SpringBootTest
public class ProductTest {

    @Mock
    private ProductRepository productRepository;

    private ProductInterface productInterface;
    /**
     * Sets up the testing environment before each test, initializes mocks and the product service interface.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        productInterface = new ProductService(productRepository);
    }
    /**
     * Verifies that the save operation works correctly by ensuring that the repository's save method is called with the correct product.
     */
    @Test
    public void saveProductTest(){
        Product expectedProduct = new Product("Vitamina D", Category.VITAMINS, 40, 10, LocalDate.of(2024,10, 5));

        //  when(ProductRepository.save(expectedProduct)).thenReturn(expectedProduct);
        productInterface.createProduct(expectedProduct);

        verify(productRepository).save(expectedProduct);
        //assertEquals(expectedProduct, savedProduct);

    }
    /**
     * Tests the update functionality by verifying that the findById and save methods are invoked correctly with the proper product details.
     */
    @Test
    public void updateProductTest(){
        Product existingProduct = new Product("Vitamina D", Category.VITAMINS, 40, 10, LocalDate.of(2024,10, 5));
        Product updatedProduct = new Product("Vitamina D", Category.VITAMINS, 50, 10, LocalDate.of(2024,10, 5));


        when(productRepository.findById(existingProduct.getId())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        productInterface.updateProduct(updatedProduct);

        verify(productRepository).findById(existingProduct.getId());
        verify(productRepository).save(updatedProduct);

        //assertEquals(existingProduct.getId(), res.getId());
        //assertEquals(expectedProduct, savedProduct);
    }
    /**
     * Ensures that the delete operation is tested by verifying the deletion process through the repository's deleteById method.
     */
    @Test
    public void deletedProductTest(){
        Product existingProduct = new Product("Vitamina D", Category.VITAMINS, 40, 10, LocalDate.of(2024,10, 5));

        when(productRepository.findById(existingProduct.getId())).thenReturn(Optional.of(existingProduct));

        productInterface.deleteProduct(existingProduct.getId());

        verify(productRepository).deleteById(existingProduct.getId());
    }
    /**
     * Validates the retrieval of a product by ID by ensuring that the repository's findById method is called.
     */
    @Test
    public void getIdProductTest(){
        Product existingProduct = new Product("Vitamina D", Category.VITAMINS, 40, 10, LocalDate.of(2024,10, 5));

        when(productRepository.findById(existingProduct.getId())).thenReturn(Optional.of(existingProduct));

        productInterface.getProductById(existingProduct.getId());

        verify(productRepository).findById(existingProduct.getId());
    }
    /**
     * Tests the retrieval of a product by name to verify the correct operation of the repository's findByName method.
     */
    @Test
    public void getNameProductTest(){
        Product existingProduct = new Product("Vitamina D", Category.VITAMINS, 40, 10, LocalDate.of(2024,10, 5));

        when(productRepository.findByName(existingProduct.getName())).thenReturn(Optional.of(existingProduct));

        productInterface.getProductByName(existingProduct.getName());

        verify(productRepository).findByName(existingProduct.getName());
    }

    /**
     * Confirms that all products are retrieved correctly by checking the findAll method of the repository.
     */
    @Test
    public void getAllProductTest(){
        Product product1 = new Product("Vitamina D", Category.VITAMINS, 40, 10, LocalDate.of(2024,10, 5));
        Product product2 = new Product("Vitamina B3", Category.VITAMINS, 100, 20, LocalDate.of(2024,9, 9));

        List<Product> products = Arrays.asList(product1,product2);

        when(productRepository.findAll()).thenReturn(products);

        productInterface.getAllProducts();

        verify(productRepository).findAll();

    }
}
