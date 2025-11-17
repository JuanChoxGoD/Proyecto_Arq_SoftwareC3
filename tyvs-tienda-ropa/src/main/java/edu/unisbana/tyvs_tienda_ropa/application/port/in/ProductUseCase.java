package edu.unisbana.tyvs_tienda_ropa.application.port.in;

import java.util.List;
import java.util.Optional;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;

public interface ProductUseCase {

    Product createProduct(Product product);

    Optional<Product> getProductById(String id);

    List<Product> getAllProducts();

    Product updateProduct(String id, Product product);

    void deleteProduct(String id);
}
