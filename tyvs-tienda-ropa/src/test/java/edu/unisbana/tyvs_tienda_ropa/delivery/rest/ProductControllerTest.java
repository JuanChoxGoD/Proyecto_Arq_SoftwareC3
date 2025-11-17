package edu.unisbana.tyvs_tienda_ropa.delivery.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.unisbana.tyvs_tienda_ropa.application.delivery.rest.ProductController;
import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.ProductUseCase;

class ProductControllerTest {

    @Mock
    private ProductUseCase productUseCase;

    @InjectMocks
    private ProductController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfProducts() {
        when(productUseCase.getAllProducts())
                .thenReturn(List.of(new Product("1", "Camisa", 100000.0, 5)));

        ResponseEntity<List<Product>> response = controller.getAllProducts();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        verify(productUseCase).getAllProducts();
    }

    @Test
    void shouldCreateProduct() {
        Product product = new Product("1", "Camisa", 100000.0, 5);

        when(productUseCase.createProduct(product))
                .thenReturn(product);

        ResponseEntity<Product> response = controller.createProduct(product);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Camisa", response.getBody().getName());
        verify(productUseCase).createProduct(product);
    }
}
