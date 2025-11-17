package edu.unisbana.tyvs_tienda_ropa.infraestructure.persistence.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;
import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.adapter.ProductRepositoryAdapter;
import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.entity.ProductEntity;
import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.jpa.JpaProductRepository;

class ProductRepositoryAdapterTest {

    @Mock
    private JpaProductRepository jpaRepository;

    @InjectMocks
    private ProductRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnMappedDomainProducts() {
        ProductEntity e1 = new ProductEntity();
        e1.setId(1L); e1.setName("Camisa"); e1.setPrice(50000.0); e1.setStock(10);
        ProductEntity e2 = new ProductEntity();
        e2.setId(2L); e2.setName("Pantalón"); e2.setPrice(80000.0); e2.setStock(5);

        when(jpaRepository.findAll()).thenReturn(List.of(e1, e2));

        List<Product> result = adapter.findAll();

        assertEquals(2, result.size());
        assertEquals("Camisa", result.get(0).getName());
        assertEquals(80000.0, result.get(1).getPrice());
        verify(jpaRepository, times(1)).findAll();
    }

    @Test
    void save_shouldPersistAndReturnDomainProduct() {
        Product domain = new Product(null, "Zapatos", 120000.0, 8);
        ProductEntity savedEntity = new ProductEntity();
        savedEntity.setId(3L); savedEntity.setName("Zapatos"); savedEntity.setPrice(120000.0); savedEntity.setStock(8);

        // adapter builds an entity from domain and calls jpaRepository.save(...)
        when(jpaRepository.save(any(ProductEntity.class))).thenReturn(savedEntity);

        Product result = adapter.save(domain);

        assertNotNull(result.getId());
        assertEquals(3L, result.getId());
        assertEquals("Zapatos", result.getName());
        verify(jpaRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void findById_shouldReturnEmptyWhenNotFound() {
        when(jpaRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Product> opt = adapter.findById(99L);// The method findById(Long) in the type ProductRepositoryAdapter is not applicable for the arguments (String)Java(67108979
        assertTrue(opt.isEmpty());
        verify(jpaRepository).findById(99L);
    }
}
