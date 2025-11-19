package edu.unisbana.tyvs_tienda_ropa.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.ProductUseCase;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.ProductRepositoryPort;

@Service
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductRepositoryPort productRepository;

    public ProductUseCaseImpl(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    // ---- VALIDACIÓN MÍNIMA ----
    private void validate(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    @Override
    public Product createProduct(Product product) {
        validate(product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(String id, Product product) {
        Optional<Product> existing = productRepository.findById(id);

        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }

        validate(product); // <-- VALIDACIÓN TAMBIÉN AQUÍ

        Product toUpdate = existing.get();
        toUpdate.setName(product.getName());
        toUpdate.setPrice(product.getPrice());
        toUpdate.setStock(product.getStock());

        return productRepository.save(toUpdate);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.delete(id);
    }
}
