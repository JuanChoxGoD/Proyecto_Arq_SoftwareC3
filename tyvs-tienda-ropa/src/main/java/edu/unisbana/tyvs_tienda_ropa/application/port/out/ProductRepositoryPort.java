package edu.unisbana.tyvs_tienda_ropa.application.port.out;

import java.util.List;
import java.util.Optional;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(Long id);
    Optional<Product> findById(String id);
    
    List<Product> findAll();

    void delete(String id);
}
