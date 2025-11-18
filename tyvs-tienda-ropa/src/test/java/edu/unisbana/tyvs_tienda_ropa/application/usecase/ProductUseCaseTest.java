package edu.unisbana.tyvs_tienda_ropa.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.ProductRepositoryPort;

class ProductUseCaseTest {

    @Mock
    private ProductRepositoryPort productRepository;

    @InjectMocks
    private ProductUseCaseImpl productUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllProducts() {
        List<Product> products = Arrays.asList(
                new Product("1", "Camisa", 50000.0, 10),
                new Product("2", "Pantalón", 80000.0, 5) 
        );

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productUseCase.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void shouldSaveProductSuccessfully() {
        Product newProduct = new Product(null, "Zapatos", 120000.0, 8);
        Product savedProduct = new Product("3", "Zapatos", 120000.0, 8);

        when(productRepository.save(newProduct)).thenReturn(savedProduct);

        Product result = productUseCase.createProduct(newProduct);

        assertNotNull(result.getId());
        assertEquals("Zapatos", result.getName());
        verify(productRepository, times(1)).save(newProduct);
    }

    //  TEST NEGATIVO AÑADIDO (lo único nuevo)
    @Test
    void shouldThrowExceptionWhenPriceIsInvalid() {
        Product invalid = new Product(null, "Correa", -20000.0, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            productUseCase.createProduct(invalid);
        });
    }
}
